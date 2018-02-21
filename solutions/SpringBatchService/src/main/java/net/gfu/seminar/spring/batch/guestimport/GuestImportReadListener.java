package net.gfu.seminar.spring.batch.guestimport;

import org.springframework.batch.core.ItemReadListener;
import org.springframework.batch.item.file.FlatFileParseException;

public class GuestImportReadListener implements ItemReadListener<Guest>{

	@Override
	public void beforeRead() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterRead(Guest item) {
		System.out.println("After read: " + item);
	}

	@Override
	public void onReadError(Exception ex) {
		System.out.println("Error occurred: " + ex.getMessage());
		if (ex instanceof FlatFileParseException) {
			FlatFileParseException ffpe = (FlatFileParseException) ex;
			System.out.println("Corrupt line: " +ffpe.getLineNumber());
			System.out.println("Corrupt content: " +ffpe.getInput());
		}
	}

}
