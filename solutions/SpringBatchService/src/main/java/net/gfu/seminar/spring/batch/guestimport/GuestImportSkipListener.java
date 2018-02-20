package net.gfu.seminar.spring.batch.guestimport;

import org.springframework.batch.core.SkipListener;

public class GuestImportSkipListener implements SkipListener<Guest, Guest> {

	@Override
	public void onSkipInRead(Throwable t) {
		System.out.println("Item skipped: " + t.getMessage());
	}

	@Override
	public void onSkipInWrite(Guest item, Throwable t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSkipInProcess(Guest item, Throwable t) {
		// TODO Auto-generated method stub

	}

}
