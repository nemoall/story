package com.story.ImportExcel.bankcard;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.*;

/**
 * blingbling ✨
 */
public class BankcardTest {
    public static void main(String[] args) throws Exception {
        importExcelAction();
    }

    //导入Excel数据
    public static void importExcelAction() throws Exception {
        //文件路径

        String filePath = "/Users/ll/excel/shop.xlsx";

        XSSFWorkbook wookbook = new XSSFWorkbook(new FileInputStream(filePath));

        XSSFSheet sheet = wookbook.getSheet("Sheet1");

        //获取到Excel文件中的所有行数

        int rows = sheet.getPhysicalNumberOfRows();

        List<Bankcard> bankcardList = new ArrayList<>();

        for (int i = 1; i < rows; i++) {

            // 读取左上端单元格

            XSSFRow row = sheet.getRow(i);

            // 行不为空

            if (row != null) {

                Map<String, Object> map = new HashMap<String, Object>();

                //获取到Excel文件中的所有的列

                int cells = row.getPhysicalNumberOfCells();

                bankcardList.add(
                        new Bankcard(
                                getValue(row.getCell(4)),
                                getValue(row.getCell(6)),
                                getValue(row.getCell(5)),
                                getValue(row.getCell(7)),
                                getValue(row.getCell(0))
                        ));


            }

        }

        String sql = "";

        String bankcardHead = "INSERT INTO tnt_biz_erp_bankcard (\n" +
                "\tbankcard_uid,\n" +
                "\tbank_name,\n" +
                "\tbank_account,\n" +
                "\tbank_account_name,\n" +
                "\tbusiness_uid,\n" +
                "\tbusiness_type,\n" +
                "\tis_default,\n" +
                "\tcreated_at,\n" +
                "\tcreated_by,\n" +
                "\tupdated_at,\n" +
                "\tupdated_by \n" +
                ") SELECT\n" +
                "tnt_uuid_short () AS bankcard_uid,";

        for (Bankcard bankcardb : bankcardList) {
            sql += bankcardHead + "'"
                    + bankcardb.getBankName() + "'as bank_name,'"
                    + bankcardb.getBankAccount() + "'as bank_account,'"
                    + bankcardb.getBankAccountName() + "'as bank_account_name,"
                    + "shop_uid AS business_uid,\n" +
                    "'TYP_ERP_SHOP' AS business_type," + "'"
                    + (Objects.equals("是", bankcardb.getIsDefault()) ? "TRUE" : "FALSE")+"' as is_default,"
                    + "now() as created_at,\n" +
                    "'系统管理员' as created_by,\n" +
                    "now() as updated_at\n" +
                    "FROM\n" +
                    "\ttnt_biz_erp_shop_info \n" +
                    "WHERE\n" +
                    "\tshop_code = '" + bankcardb.getShopCode() + "';\n";
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
