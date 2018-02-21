package net.gfu.seminar.spring.batch.guestimport;

import org.springframework.batch.item.ItemProcessor;

public class GuestItemProcessor implements ItemProcessor<Guest, Guest> {

	private boolean hasAllReadyFailed = false;
	
	@Override
	public Guest process(final Guest guest) throws Exception {
		final String firstName = guest.getFirstName().toUpperCase();
		final String lastName = guest.getLastName().toUpperCase();
		final Guest transformedGuest = new Guest(firstName, lastName);

		System.out.println("Converted (" + guest + ") into ("
				+ transformedGuest + ")");
		
		if(!hasAllReadyFailed && transformedGuest.getLastName().equalsIgnoreCase("Krachers")) {
			transformedGuest.setLastName(null);
			hasAllReadyFailed = true;
		} else if(transformedGuest.getLastName()==null) {
			transformedGuest.setLastName("Krachersfixed");
		}

		return transformedGuest;
	}

}