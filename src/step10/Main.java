package step10;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		ServiceCollection serviceCollection = new ServiceCollection() ;
		Invoice invoice = new Invoice();
		InvoiceWriter invoiceWriter = new InvoiceWriter();
		RecordReader recordreader = new RecordReader();
		Record record = recordreader.read();
		while (record != null) {
			switch (record.getRecordCode()) {
			case '1':
				serviceCollection.clear(); // サービス加入情報を初期化する
				invoice.clear(); // 請求データを初期化する
				invoice.setOwnerTelNumber(record.getOwnerTelNumber());
				break;

			case '2':
				serviceCollection.checkService(record);
				break;

			case '5':
				int unitPrice = 20;
				unitPrice = serviceCollection.calcUnitPrice(record, unitPrice); // 通話単価を求める
				int callTime = record.getCallMinutes();
				int callCharge = unitPrice * callTime;
				invoice.addCallCharge(callCharge);
				break;

			case '9':
				int basicCharge = 1000;
				basicCharge = serviceCollection.calcBasicCharge(basicCharge);
				invoice.setBasicCharge(basicCharge);

				invoiceWriter.write(invoice);

				break;
			}

			record = recordreader.read();
		}
		recordreader.close();
		invoiceWriter.close();
	}

}