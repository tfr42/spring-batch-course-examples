package net.gfu.seminar.spring.data.rest;

import java.util.List;


import net.gfu.seminar.spring.batch.guestimport.Guest;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * A RESTful repository serving Guests.
 */
 @RepositoryRestResource(collectionResourceRel = "guests", path = "guests")
 public interface GuestRepository extends PagingAndSortingRepository<Guest, Long> {

        List<Guest> findByLastName(@Param("lastName") String lastName);
        List<Guest> findByLastNameIgnoreCase(@Param("lastName") String lastName);

}
