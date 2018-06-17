package edu.srh.aupair.contractGenerationOperations;

import java.sql.ResultSet;
import java.sql.SQLException;

import edu.srh.aupair.userProfileOperations.IUserProfileOperationsInterface;
import edu.srh.aupair.userProfileOperations.UserProfileOperationsRepository;

public class ContractGenerationService
	implements IContractGenerationInterface{
		ContractGenerationRepository repository;
		public ContractGenerationService() throws SQLException {
			repository=new ContractGenerationRepository();
		}

		public void DynamicJasperReport(){
			try {
				repository.DynamicJasperReport();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
}
