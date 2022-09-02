package com.dillip.api.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.dillip.api.dto.BillFromDetails;
import com.dillip.api.dto.BillToDetails;
import com.dillip.api.dto.ProductDetails;
import com.dillip.api.dto.ShipToDetails;
import com.dillip.api.dto.TotalProductDetails;
import com.dillip.api.dto.TransportDetails;
import com.dillip.api.dto.UniversityDetailsDTO;
import com.dillip.api.entity.ContactDetails;
import com.dillip.api.entity.ReportEntity;
import com.dillip.api.repository.ReportEntityRepository;
import com.dillip.api.request.WeightSlipRequest;
import com.dillip.api.response.ConsumeUniversityBody;
import com.dillip.api.response.InvoiceDetailsResponse;
import com.dillip.api.response.MediaFile;
import com.dillip.api.response.ReportResponse;
import com.dillip.api.util.ProjectConstant;
import com.google.gson.Gson;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private ReportEntityRepository repository;

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private CommonServiceImpl commonServiceImpl;
	
	@Autowired
	private Gson gson;

	private final static Logger LOGGER = Logger.getLogger("Dillip Logger");
	
	@Value("${universitydetails.url}")
	private String universityDetailsUrl;

	@Override
	public String startReportApi() {
		LOGGER.log(Level.INFO,
				"########## API has been Started :: Status :: UP :: SUCCESS ##########");
		String message = ProjectConstant.SUCCESS_MSG;
		return message;
	}

	@Override
	public MediaFile exportReport(WeightSlipRequest weightSlipRequest) throws JRException, IOException {
		
		LOGGER.log(Level.INFO, "########## Hitting exportReport() method in ServiceImpl Layer ##########");

		String fileName = "Weight Slip_" + weightSlipRequest.getVehicleNumber().toUpperCase() + "_"
				+ ProjectConstant.formattedDateTime(LocalDateTime.now()) + ".pdf";

		if (weightSlipRequest.isChecked()) {
			saveWeightSlipDetails(weightSlipRequest);
			LOGGER.log(Level.INFO,
					"########## Weight Slip Details Saved Successfully in the PostgresSQL Database ##########");
		}

		List<WeightSlipRequest> list = new ArrayList<>();
		list.add(new WeightSlipRequest());

		String netWeight = String.valueOf(Integer.parseInt(weightSlipRequest.getGrossWeight())
				- Integer.parseInt(weightSlipRequest.getTareWeight()));

		ClassPathResource classPathResource = new ClassPathResource("WeightSlip.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(classPathResource.getInputStream());
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("address", weightSlipRequest.getAddress());
		parameters.put("vehicleNumber", weightSlipRequest.getVehicleNumber());
		parameters.put("grossWeight", weightSlipRequest.getGrossWeight());
		parameters.put("tareWeight", weightSlipRequest.getTareWeight());
		parameters.put("netWeight", netWeight);
		parameters.put("grossWeightDate", formattedDate(weightSlipRequest.getGrossWeightDate()));
		parameters.put("tareWeightDate", formattedDate(weightSlipRequest.getTareWeightDate()));
		parameters.put("grossWeightTime", formattedTime(weightSlipRequest.getGrossWeightDate()));
		parameters.put("tareWeightTime", formattedTime(weightSlipRequest.getTareWeightDate()));

		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
		byte[] data = JasperExportManager.exportReportToPdf(jasperPrint);

		HttpHeaders headers = new HttpHeaders();

		MediaFile mediaFile = new MediaFile();
		mediaFile.setFileName(fileName);
		mediaFile.setByteData(data);

		Date date = new Date();
		headers.set(HttpHeaders.CONTENT_DISPOSITION,
				"attachment;filename=Weight Slip_" + String.valueOf(date) + ".pdf");
		LOGGER.log(Level.INFO, "########## Report Generated in PDF ......... ##########");
		return mediaFile;
	}
	
	@Override
//	public MediaFile generateInvoice() throws JRException, IOException{
	public ResponseEntity<byte[]> generateInvoice() throws JRException, IOException{

//		LOGGER.log(Level.INFO, "########## Hitting exportReport() method in ServiceImpl Layer ##########");

		String fileName = "abc.pdf";
		
		InvoiceDetailsResponse invoiceDetailsResponse = new InvoiceDetailsResponse();
		BillFromDetails billFromDetails = new BillFromDetails();
		TransportDetails transportDetails = new TransportDetails();
		BillToDetails billToDetails = new BillToDetails();
		ShipToDetails shipToDetails = new ShipToDetails();
		
		TotalProductDetails totalProductDetails = new TotalProductDetails();
		
		billFromDetails.setReverseCharge("No");
		billFromDetails.setInvoiceNo("MMT/2022/128");
		billFromDetails.setInvoiceDate("23-05-2022");
		billFromDetails.setBillFromState("Odisha");
		billFromDetails.setBillFromStateCode("21");
		
		transportDetails.setTransMode("BY ROAD");
		transportDetails.setVehicleNo("OD02AB8329");
		transportDetails.setSupplyDate("23-05-2022");
		transportDetails.setSupplyPlace("Boudh");
		transportDetails.setTransporterName("TIRUPATI TRANSPORT");
		
		billToDetails.setBillToName("MAA BHGABATI INFRATECH");
		billToDetails.setBillToAddress("PLOT NO-3/B-07HITECH KALYANI PLAZA, KANTILO, KHUA, AIRFIELD, BHUBANESWAR, 751002");
		billToDetails.setBillToGstin("21AJOPA9580A2ZI");
		billToDetails.setBillToState("Odisha");
		billToDetails.setBillToStateCode("21");
		
		shipToDetails.setShipToName("MAA BHGABATI INFRATECH");
		shipToDetails.setShipToAddress("PLOT NO-3/B-07HITECH KALYANI PLAZA, KANTILO, KHUA, AIRFIELD, BHUBANESWAR, 751002");
		shipToDetails.setShipToGstin("21AJOPA9580A2ZI");
		shipToDetails.setShipToState("Odisha");
		shipToDetails.setShipToStateCode("21");
		
		totalProductDetails.setTotalQuantity("19440");
		totalProductDetails.setTotalTaxableValue("Rs. 7,19,280.00");
		totalProductDetails.setTotalCgstAmount("Rs. 64735.2");
		totalProductDetails.setTotalSgstAmount("Rs. 64735.2");
		totalProductDetails.setTotalAllAmount("Rs. 8,48,750.00");
		totalProductDetails.setGstAmount("Rs. 1,29,470.40");
		totalProductDetails.setTotalAmtInWord("Eight Lakh Forty Eight Thousand Seven Hundred Fifty Rupees Only");
		

		ClassPathResource classPathResource = new ClassPathResource("invoice.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(classPathResource.getInputStream());
		
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("reverseCharge", billFromDetails.getReverseCharge());
		parameters.put("invoiceNo", billFromDetails.getInvoiceNo());
		parameters.put("invoiceDate", billFromDetails.getInvoiceDate());
		parameters.put("billFromState", billFromDetails.getBillFromState());
		parameters.put("billFromStateCode", billFromDetails.getBillFromStateCode());
		parameters.put("transMode", transportDetails.getTransMode());
		parameters.put("vehicleNo", transportDetails.getVehicleNo());
		parameters.put("supplyDate", transportDetails.getSupplyDate());
		parameters.put("supplyPlace", transportDetails.getSupplyPlace());
		parameters.put("transporterName", transportDetails.getTransporterName());
		parameters.put("billToName", billToDetails.getBillToName());
		parameters.put("billToAddress", billToDetails.getBillToAddress());
		parameters.put("billToGstin", billToDetails.getBillToGstin());
		parameters.put("billToState", billToDetails.getBillToState());
		parameters.put("billToStateCode", billToDetails.getBillToStateCode());
		parameters.put("shipToName", shipToDetails.getShipToName());
		parameters.put("shipToAddress", shipToDetails.getShipToAddress());
		parameters.put("shipToGstin", shipToDetails.getShipToGstin());
		parameters.put("shipToState", shipToDetails.getShipToState());
		parameters.put("shipToStateCode", shipToDetails.getShipToStateCode());
//		parameters.put("totalQuantity", totalProductDetails.getTotalQuantity());
		parameters.put("totalTaxableValue", totalProductDetails.getTotalTaxableValue());
		parameters.put("totalCgstAmount", totalProductDetails.getTotalCgstAmount());
		parameters.put("totalSgstAmount", totalProductDetails.getTotalSgstAmount());
		parameters.put("totalAllAmount", totalProductDetails.getTotalAllAmount());
		parameters.put("gstAmount", totalProductDetails.getGstAmount());
		parameters.put("totalAmtInWord", totalProductDetails.getTotalAmtInWord());
		parameters.put("subReport", getSubReport());
		parameters.put("subReportDataSource", getSubReportDataSource());
		parameters.put("subReportParameter", getSubReportParameter());
		

		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
		byte[] data = JasperExportManager.exportReportToPdf(jasperPrint);

		HttpHeaders headers = new HttpHeaders();

		MediaFile mediaFile = new MediaFile();
		mediaFile.setFileName(fileName);
		mediaFile.setByteData(data);

		Date date = new Date();
		headers.set(HttpHeaders.CONTENT_DISPOSITION,
				"attachment;filename=Invoice_" + String.valueOf(date) + ".pdf");
		LOGGER.log(Level.INFO, "########## Report Generated in PDF ......... ##########");
//		return mediaFile;
		return new ResponseEntity<>(data, headers, HttpStatus.OK);
	}
	
	public JasperReport getSubReport()
	{
		ClassPathResource classPathResource = new ClassPathResource("invoice_sub_report.jrxml");
		try {
			JasperReport jasperReport = JasperCompileManager.compileReport(classPathResource.getInputStream());
			return jasperReport;
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public JRBeanCollectionDataSource getSubReportDataSource()
	{
		List<ProductDetails> productDetailsList = new LinkedList<>();
		productDetailsList.add(new ProductDetails("1","MS SCRAP", "7204", "19440", "KGS", "37", "7,19,280.00", "9.00%", "64735.2", "9.00%", "64735.2", "8,48,750.40"));
		productDetailsList.add(new ProductDetails("2","OLD KRAFT", "4207", "4201", "KGS", "21", "1,21,000.00", "2.50%", "64735.2", "2.50%", "64735.2", "8,48,750.40"));
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(productDetailsList);
		return dataSource;
	}
	
	public Map<String, Object> getSubReportParameter()
	{
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("totalQuantity", "19440");
		parameters.put("totalTaxableValue", "Rs. 7,19,280.00");
		parameters.put("totalCgstAmount", "Rs. 64,735.2");
		parameters.put("totalSgstAmount", "Rs. 64,735.2");
		parameters.put("totalAllAmount", "Rs. 8,48,750.00");
		return parameters;
	}

	public String formattedDate(LocalDateTime date) {
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return date.format(myFormatObj);
	}

	public String formattedTime(LocalDateTime date) {
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("hh : mm : ss  a");
		return date.format(myFormatObj).toUpperCase();
	}

	public String formattedDateTime(LocalDateTime date) {
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd MMMM yyyy hh:mm:ss a");
		return date.format(myFormatObj).toUpperCase();
	}

	public void saveWeightSlipDetails(WeightSlipRequest weightSlipRequest) {
		ReportEntity entity = new ReportEntity();
		String netWeight = String.valueOf(Integer.parseInt(weightSlipRequest.getGrossWeight())
				- Integer.parseInt(weightSlipRequest.getTareWeight()));
		
		LOGGER.log(Level.INFO, "########## Entered into saveWeightSlipDetails() :: WeightSlipRequest :: "+weightSlipRequest);

		try {
			entity.setAddress(weightSlipRequest.getAddress().toUpperCase());
			entity.setVehicleNumber(weightSlipRequest.getVehicleNumber().toUpperCase());
			entity.setGrossWeight(weightSlipRequest.getGrossWeight());
			entity.setTareWeight(weightSlipRequest.getTareWeight());
			entity.setNetWeight(netWeight);
			entity.setGrossWeightDate(weightSlipRequest.getGrossWeightDate());
			entity.setTareWeightDate(weightSlipRequest.getTareWeightDate());
			entity.setCreatedDate( ProjectConstant.convertUTCtoISTtime(LocalDateTime.now()));
			repository.save(entity);
		} catch (Exception e) {
			LOGGER.log(Level.INFO, "########## Exception Occured While Saving the Weight Slip Details in saveWeightSlipDetails() in ServiceImpl Layer ##########"+e);
		}
	}

	@Override
	public List<ReportResponse> findAllWeightSlipDetails() {
		List<ReportEntity> response = repository.findAll();
		List<ReportResponse> reRespopnse = new ArrayList<>();

		try {
			LOGGER.log(Level.INFO, "########## Hitting findAllWeightSlipDetails() for getting all WeightSlip Details in ServiceImpl Layer ##########");
			for (ReportEntity report : response) {
				ReportResponse obj = new ReportResponse();
				obj.setAddress(report.getAddress());
				obj.setVehicleNumber(report.getVehicleNumber());
				obj.setGrossWeight(report.getGrossWeight()+" KG");
				obj.setTareWeight(report.getTareWeight()+ " KG");
				obj.setNetWeight(report.getNetWeight()+ " KG");
				obj.setGrossWeightDate(formattedDateTime(report.getGrossWeightDate()));
				obj.setTareWeightDate(formattedDateTime(report.getTareWeightDate()));
				obj.setCreatedDate(formattedDateTime(report.getCreatedDate()));

				reRespopnse.add(obj);
			}
		} catch (Exception e) {
			LOGGER.log(Level.INFO, "########## Exception Occured while fetching the Weight Slip Details in findAllWeightSlipDetails() in ServiceImpl Layer ##########"+e);
		}
		return reRespopnse;
	}

	@Override
	public List<ReportResponse> findByVehicleNumber(String vehicleNumber) {
		List<ReportEntity> response = repository.findByVehicleNumber(vehicleNumber);
		List<ReportResponse> reRespopnse = new ArrayList<>();

		try {
			LOGGER.log(Level.INFO, "########## Entered in findByVehicleNumber() in ServiceImpl Layer ##########");
			for (ReportEntity report : response) {
				ReportResponse obj = new ReportResponse();
				obj.setAddress(report.getAddress());
				obj.setVehicleNumber(report.getVehicleNumber());
				obj.setGrossWeight(report.getGrossWeight());
				obj.setTareWeight(report.getTareWeight());
				obj.setNetWeight(report.getNetWeight());
				obj.setGrossWeightDate(formattedDateTime(report.getGrossWeightDate()));
				obj.setTareWeightDate(formattedDateTime(report.getTareWeightDate()));
				obj.setCreatedDate(formattedDateTime(report.getCreatedDate()));

				reRespopnse.add(obj);
			}
		} catch (Exception e) {
			LOGGER.log(Level.INFO, "########## Exception Occured while fetching the Weight Slip Details in findByVehicleNumber() in ServiceImpl Layer ########## "+e);
		}
		return reRespopnse;
	}

	@Override
	public String deleteAllWeightSlip() {
		repository.deleteAll();
		LOGGER.log(Level.INFO, "########## Entered into deleteAllWeightSlip() in ServiceImpl Layer ##########");
		String message = ProjectConstant.SUCCESS_MSG;
		return message;
	}

	@Override
	public String sendEmail(ContactDetails contact) {
		
		LOGGER.log(Level.INFO, "########## Entered into sendEmail() in ServiceImpl Layer ##########");

		String emailBody = "Dear, " + contact.getName() + "\n\n"
				+ "I hope you are having a productive day.\n\nI greatly appreciate the time you spent for visiting my Portfolio.\n\n"
				+ "Thank you for sharing your valuable feedback - Keep in Touch"
				+ "\n\nNOTE: This is an auto generated mail. Please do not reply to this message or on this email address.\n\n"
				+ "Thanks & Regards \nDillip K Sahoo\nContact Number :- +91 8117941692\nMailto:- lit.dillip2017@gmail.com\nWebsite:- https://dillipfolio.web.app";

		String subject = "Welcome to DillipFolio – Thanks for Visiting !!";
		
		LOGGER.log(Level.INFO, "########## Email Body ########## :: Email Content :: "+emailBody);

		SimpleMailMessage message = new SimpleMailMessage();

		message.setFrom("dongjinmaster9@gmail.com");
		message.setTo(contact.getEmail());
		message.setText(emailBody);
		message.setSubject(subject);

		mailSender.send(message);

		LOGGER.log(Level.INFO, "########## Mail has been send Successfully :: SUCCESS ##########");

		ContactDetails contactDetails = new ContactDetails();
		contactDetails.setName(contact.getName());
		contactDetails.setEmail(contact.getEmail());
		contactDetails.setMessage(contact.getMessage());
		contactDetails.setSubject(contact.getSubject());

		return ProjectConstant.SUCCESS_MSG;
	}

	@Override
	public List<UniversityDetailsDTO> getUniversityDetailsByCountryName(String countryName) {
		List<ConsumeUniversityBody> body = null;
		List<UniversityDetailsDTO> listUniversity = new ArrayList<UniversityDetailsDTO>();
		String apiUrl = universityDetailsUrl+"?country=" + countryName;

		try {
			LOGGER.log(Level.INFO, "########## Entered in getUniversityDetailsByCountryName() in ServiceImpl Layer :: apiUrl :: "+apiUrl);
			String fetchDataFromOtherApi = commonServiceImpl.fetchDataFromOtherApi(apiUrl);
			ConsumeUniversityBody[] fromJson = gson.fromJson(fetchDataFromOtherApi, ConsumeUniversityBody[].class);
			body = Arrays.asList(fromJson);

			for (int i = 0; i < body.size(); i++) {
				UniversityDetailsDTO dto = new UniversityDetailsDTO();

				dto.setCountryCode(body.get(i).getAlpha_two_code());
				dto.setCountryName(body.get(i).getCountry());
				dto.setUniversityName(body.get(i).getName());
				dto.setUniversityWebsite(body.get(i).getWeb_pages()[0]);	

				listUniversity.add(dto);
			}
		} catch (Exception e) {
			LOGGER.log(Level.INFO, "########## Exception Occured in getUniversityDetailsByCountryName() in ServiceImpl Layer ########## "+e);
		}
		return listUniversity;
	}
}
