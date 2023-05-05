package com.valrazi.cinemadb.service;

import com.valrazi.cinemadb.model.Booking;
import com.valrazi.cinemadb.model.SeatReserved;
import com.valrazi.cinemadb.repository.BookingRepository;
import com.valrazi.cinemadb.repository.SeatReservedRepository;
import com.valrazi.cinemadb.response.ResponseHandler;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookingService {
    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    SeatReservedRepository seatReservedRepository;

    Logger logger = LoggerFactory.getLogger(BookingService.class);

    //get all booking
    public ResponseEntity<Object> getAllBooking(){
        try{
            logger.trace("");
            return ResponseHandler.generateResponse("data", HttpStatus.OK, bookingRepository.findAll());
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }

    //add booking
    public  ResponseEntity<Object> addNewBooking(Booking booking){
        try{
            SeatReserved newSeatReserve = new SeatReserved();
            newSeatReserve.setSeat(booking.getSeat());
            newSeatReserve.setSchedules(booking.getSchedules());
            seatReservedRepository.save(newSeatReserve);
            return ResponseHandler.generateResponse("data", HttpStatus.OK, bookingRepository.save(booking));
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }

    //get detail booking
    public ResponseEntity<Object> getBookingDetail(String bookingCodeParams){
        try{
            List<Booking> bookingData = bookingRepository.findByBookingCode(bookingCodeParams);
            File file = ResourceUtils.getFile("classpath:report.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
            Map parameters = new HashMap();
            parameters.put("Created By", "Kelompok 4");

            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(bookingData);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
            JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\Users\\Fahrazi\\Documents\\CSV\\" + LocalDate.now() + ".pdf");

            return  ResponseHandler.generateResponse("Success", HttpStatus.OK, bookingData);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }


}
