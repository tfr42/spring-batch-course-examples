package net.gfu.seminar.spring.batch.guestimport;

import java.util.Iterator;
import java.util.List;

import org.springframework.batch.core.ItemWriteListener;

public class GuestImportWriteListener implements ItemWriteListener<Guest> {

	@Override
	public void beforeWrite(List<? extends Guest> items) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterWrite(List<? extends Guest> items) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onWriteError(Exception exception, List<? extends Guest> items) {
		System.out.println("Error occurred: " + exception.getMessage());
		for (Iterator iterator = items.iterator(); iterator.hasNext();) {
			Guest guest = (Guest) iterator.next();
			System.out.println("Failed to write: " + guest);
		}
		
	}

}
