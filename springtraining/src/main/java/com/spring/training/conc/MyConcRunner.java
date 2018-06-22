package com.spring.training.conc;

import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
public class MyConcRunner implements CommandLineRunner {
	@Autowired
	private MyAsyncBean asynBean;

	@Override
	public void run(String... args) throws Exception {
		Future<String> testAsync = asynBean.testAsync();
		System.out.println("Buradayım");
		String string = testAsync.get();
		System.out.println("Sonuç : "
		                   + string);

		ListenableFuture<String> testAsync2 = asynBean.testAsync2();
		testAsync2.addCallback(new ListenableFutureCallback<String>() {

			@Override
			public void onSuccess(String result) {
				System.out.println("Listen result : "
				                   + result
				                   + " prev result :"
				                   + string);
			}

			@Override
			public void onFailure(Throwable ex) {
				System.err.println("Error");
			}
		});
		System.out.println("Devam");
	}

}
