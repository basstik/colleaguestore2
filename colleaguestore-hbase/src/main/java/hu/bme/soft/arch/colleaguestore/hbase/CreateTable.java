package hu.bme.soft.arch.colleaguestore.hbase;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.HBaseAdmin;

public class CreateTable {

	private static final String HBASE_MASTER = "hbase.master";
	public static final String HBASE_CONFIGURATION_ZOOKEEPER_QUORUM = "hbase.zookeeper.quorum";
	public static final String HBASE_CONFIGURATION_ZOOKEEPER_CLIENTPORT = "hbase.zookeeper.property.clientPort";

	public static void main(String[] args) throws IOException {

		// Instantiating configuration class
		Configuration con = HBaseConfiguration.create();

		con.set(HBASE_CONFIGURATION_ZOOKEEPER_QUORUM, "192.168.100.57");
		con.setInt(HBASE_CONFIGURATION_ZOOKEEPER_CLIENTPORT, 10000);
		con.set(HBASE_MASTER, "192.168.100.57:60000");

		// Instantiating HbaseAdmin class
		HBaseAdmin admin = new HBaseAdmin(con);
		// HBaseAdmin.checkHBaseAvailable(con);
		System.out.println("HBase is running!");

		// Instantiating table descriptor class
		HTableDescriptor tableDescriptor = new HTableDescriptor("emp3");

		// Adding column families to table descriptor
		tableDescriptor.addFamily(new HColumnDescriptor("personal"));
		tableDescriptor.addFamily(new HColumnDescriptor("professional"));

		// Execute the table through admin
		admin.createTable(tableDescriptor);
		System.out.println(" Table created ");

	}
}