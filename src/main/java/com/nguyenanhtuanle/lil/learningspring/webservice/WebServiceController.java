package com.nguyenanhtuanle.lil.learningspring.webservice;

import com.nguyenanhtuanle.lil.learningspring.business.ReservationService;
import com.nguyenanhtuanle.lil.learningspring.business.RoomReservation;
import com.nguyenanhtuanle.lil.learningspring.data.Guest;
import com.nguyenanhtuanle.lil.learningspring.data.Room;
import com.nguyenanhtuanle.lil.learningspring.util.DateUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class WebServiceController {
    private final DateUtils dateUtils;

    private final ReservationService reservationService;

    public WebServiceController(DateUtils dateUtils, ReservationService reservationService) {
        this.dateUtils = dateUtils;
        this.reservationService = reservationService;
    }

    @RequestMapping(path = "/reservations", method = RequestMethod.GET)
    public Iterable<RoomReservation> getReservations(@RequestParam(value = "date", required = false) String dateString) {
        return this.reservationService.getRoomReservationsForDate(this.dateUtils.createDateFromDateString(dateString));
    }

    @GetMapping("/guests")
    public List<Guest> getGuests() {
        return this.reservationService.getHotelGuests();
    }

    @PostMapping("/guests")
    @ResponseStatus(HttpStatus.CREATED)
    public void addGuest(@RequestBody Guest guest) {
        this.reservationService.addGuest(guest);
    }

    @GetMapping("/rooms")
    public List<Room> getRooms() {
        return this.reservationService.getRooms();
    }
}
