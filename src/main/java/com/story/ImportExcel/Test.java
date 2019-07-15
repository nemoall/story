package com.story.ImportExcel;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * blingbling ✨
 */
public class Test {
    public static void main(String[] args) throws Exception {
        importExcelAction();
    }

    //导入Excel数据
    public static void importExcelAction() throws Exception {
        //文件路径

        String filePath = "/Users/ll/excel/vendor.xlsx";

        XSSFWorkbook wookbook = new XSSFWorkbook(new FileInputStream(filePath));

        XSSFSheet sheet = wookbook.getSheet("Sheet1");

        //获取到Excel文件中的所有行数

        int rows = sheet.getPhysicalNumberOfRows();

        List<Vendor> vendorList = new ArrayList<>();

        List<Contact> contactList = new ArrayList<>();

        for (int i = 1; i < rows; i++) {

            // 读取左上端单元格

            XSSFRow row = sheet.getRow(i);

            // 行不为空

            if (row != null) {

                Map<String, Object> map = new HashMap<String, Object>();

                //获取到Excel文件中的所有的列

                int cells = row.getPhysicalNumberOfCells();

                vendorList.add(
                        new Vendor(
                                getValue(row.getCell(0)),
                                getValue(row.getCell(1)),
                                getValue(row.getCell(2)),
                                getValue(row.getCell(3)),
                                getValue(row.getCell(4)),
                                getValue(row.getCell(5)),
                                getValue(row.getCell(6)),
                                getValue(row.getCell(7)),
                                getValue(row.getCell(8))
                        ));
                contactList.add(
                        new Contact(
                                getValue(row.getCell(9)),
                                getValue(row.getCell(10)),
                                getValue(row.getCell(11)),
                                getValue(row.getCell(12)),
                                getValue(row.getCell(0))
                        )
                );

            }

        }

        String vendorhead = "insert into tnt_biz_erp_vendor_info(\n" +
                "\terp_vendor_uid,\n" +
                "\tvendor_type,\n" +

                "\terp_vendor_code,\n" +
                "\tlogin_name,\n" +
                "\tvendor_phone,\n" +
                "\tvendor_name,\n" +
                "\tvendor_id_number,\n" +
                "\tcompany_tel,\n" +
                "\tvendor_province_id,\n" +
                "\tvendor_city_id,\n" +
                "\tvendor_district_id,\n" +
                "\tvendor_address,\n" +
                "\tissuing_place,\n" +
                "\tcreated_at,\n" +
                "\tcreated_by,\n" +
                "\tupdated_at\n" +
                ")VALUES";
        String vendorBodyItem = "(tnt_uuid_short(),'TPY_POTENTIAL',";
        for (Vendor v : vendorList) {
            vendorhead += vendorBodyItem + "'"
                    + v.getVendorCode() + "','"
                    + v.getVendorPhone() + "','"
                    + v.getVendorPhone() + "','"
                    + v.getVendorName() + "','"
                    + v.getIdentityCard() + "','"
                    + v.getVendorPhone() + "','"
                    + v.getProvinceId() + "','"
                    + v.getCityId() + "','"
                    + v.getDistrictId() + "','"
                    + v.getAddress() + "','"
                    + v.getIdentityCardAddress() + "',"
                    + "now(), 'll', now())," + "\n";
        }

        System.out.println(vendorhead);

        String sql = "";

        String contactItemHead = "INSERT INTO tnt_biz_erp_contacts_info (\n" +
                "\tcontact_uid,\n" +
                "\tbusiness_uid,\n" +
                "\tbusiness_type,\n" +
                "\tcontact_name,\n" +
                "\tcontact_phone,\n" +
                "\tcontact_position,\n" +
                "\tcontact_email,\n" +
                "\tcreated_at,\n" +
                "\tcreated_by,\n" +
                "\tupdated_at \n" +
                ") SELECT\n" +
                "tnt_uuid_short() as contact_uid,\n" +
                "erp_vendor_uid AS business_uid ,\n" +
                "'TPY_POTENTIAL' as business_type,";

        for (Contact contact : contactList) {
            sql += contactItemHead + "'"
                    + contact.getContactName() + "'as contact_name,'"
                    + contact.getContactPhone() + "'as contact_phone,'"
                    + contact.getContactPosition() + "'as contact_position,'"
                    + contact.getContactEmail() + "'as contact_email,"
                    + "now() as created_at,\n" +
                    "'ll' as created_by,\n" +
                    "now() as updated_at\n" +
                    "FROM\n" +
                    "\ttnt_biz_erp_vendor_info \n" +
                    "WHERE\n" +
                    "\terp_vendor_code = '" + contact.getVendorCode() + "';\n";
        }

        System.out.println(sql);

    }

    private static String getValue(XSSFCell xSSFCell) {

        if (null == xSSFCell) {

            return "";

        }

        if (xSSFCell.getCellType() == xSSFCell.CELL_TYPE_BOOLEAN) {

            // 返回布尔类型的值

            return String.valueOf(xSSFCell.getBooleanCellValue());

        } else if (xSSFCell.getCellType() == xSSFCell.CELL_TYPE_NUMERIC) {

            // 返回数值类型的值

            return String.valueOf(xSSFCell.getNumericCellValue());

        } else {

            // 返回字符串类型的值

            return String.valueOf(xSSFCell.getStringCellValue());

        }
    }
}
