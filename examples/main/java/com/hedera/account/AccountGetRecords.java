package com.hedera.account;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;

import com.hedera.sdk.account.HederaAccount;
import com.hedera.sdk.common.HederaTransactionRecord;
import com.hederahashgraph.api.proto.java.ResponseCodeEnum;

public final class AccountGetRecords {
	public static boolean getRecords(HederaAccount account) throws Exception {
		final ch.qos.logback.classic.Logger logger = (ch.qos.logback.classic.Logger)LoggerFactory.getLogger(AccountGetRecords.class);
		
		logger.info("");
		logger.info("CRYPTO GET RECORDS");
		logger.info("");

		// get records for the account
		List<HederaTransactionRecord> records = new ArrayList<HederaTransactionRecord>();
		
		records = account.getRecords();
		if (records != null) {
			logger.info(String.format("===>Got %d records", records.size()));
			for (HederaTransactionRecord record : records) {
				logger.info("Record transaction hash");
				logger.info(record.transactionHash.toString());
			}
			return true;
		} else if (account.getPrecheckResult() == ResponseCodeEnum.BUSY) {
			logger.info("system busy, try again later");
			return false;
		} else {
			logger.info("===>Getting records - precheck ERROR" + account.getPrecheckResult().toString());
			return false;
		}
		
	}
}
