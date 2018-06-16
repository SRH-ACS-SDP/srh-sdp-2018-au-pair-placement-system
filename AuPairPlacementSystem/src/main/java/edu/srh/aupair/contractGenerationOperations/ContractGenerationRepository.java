package edu.srh.aupair.contractGenerationOperations;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class ContractGenerationRepository{
	Connection connection;

	public ContractGenerationRepository() throws SQLException {
		connection = edu.srh.aupair.utilities.utilities.getConnectionString();
	}

	public void DynamicJasperReport() throws Exception {
		File file = OpenReport();
		SaveReport(file);
	}

	public File OpenReport() throws Exception {
		File file =null;
		
		try {
			ResultSet rs = null;
			String query = "SELECT * FROM proposals INNER JOIN active_interviews ON \r\n"
					+ "proposals.ACTIVE_INTERVIEW_ID =active_interviews.ACTIVE_INTERVIEW_ID INNER JOIN hostuser \r\n"
					+ "ON active_interviews.HOST_ID=hostuser.HOST_ID INNER JOIN interview_availability ON \r\n"
					+ "interview_availability.INTERVIEW_ID=active_interviews.INTERVIEW_ID INNER JOIN au_pair ON \r\n"
					+ "AU_PAIR.AU_PAIR_ID=interview_availability.AU_PAIR_ID;";

			CallableStatement stmt = connection.prepareCall(query);
			rs = stmt.executeQuery(query);
			FastReportBuilder drb = new FastReportBuilder();
			DynamicReport dr = drb.addColumn("Tasks", "TASKS_FOR_AU_PAIR", String.class.getName(), 30)
					.addColumn("Working hours", "WORKING_HOURS_PROPOSED", String.class.getName(), 30)
					.addColumn("Remuneration", "REMUNERATIONS_PROPOSED", String.class.getName(), 50)
					.addColumn("Holidays", "HOLIDAYS_PROPOSED", String.class.getName(), 50)
					.addColumn("Travel cost", "TRAVEL_COSTS", String.class.getName(), 50).addWatermark("CONTRACT")
					.setTitle("Contract between Host family and Au-Pair")
					.setSubtitle("Below are the features agreed upon by the host family and au-pair")
					.setPrintBackgroundOnOddRows(true).setUseFullPageWidth(true).build();

			JRResultSetDataSource resultsetdatasource = new JRResultSetDataSource(rs);

			JasperPrint jp = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(),
					resultsetdatasource);
			JasperViewer.viewReport(jp);

			String currentUsersHomeDir = System.getProperty("user.home");

			JasperExportManager.exportReportToPdfFile(jp, currentUsersHomeDir + "/ContractForHostAndAuPair.pdf");

			file = new File(currentUsersHomeDir + "/ContractForHostAndAuPair.pdf");
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return file;
	}

	public void SaveReport(File file) throws Exception {
		CallableStatement stat = connection
				.prepareCall("insert into contract(AU_PAIR_ID, HOST_ID, SIGNED_CONTRACT_IMAGE) " + "values(?,?,?)");
		stat.setString(1, "1");
		stat.setString(2, "1");
		FileInputStream fis = new FileInputStream(file);
		stat.setBinaryStream(3, (InputStream) fis, (int) (file.length()));
		int s = stat.executeUpdate();
		if (s > 0) {
			System.out.println("Uploaded successfully !");
		}
	}
}
