package com.ldw.importOrOutData;

import com.ldw.dao.DataSourceDao;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 使用poi导入导出数据库中的数据
 * 使用前请在spring框架中提前注入SqlSessionFactory
 *
 * @author ludangwei
 * Created by Administrator on 2017/5/17.
 */
public class ExportData {
    @Autowired
    private SqlSessionFactory factory;
    @Autowired
    private DataSourceDao dao;

    /**
     * 根据表名称从数据库中获取表所有列的名称
     * 将数据库中拿出的数据存储进全局变量resultSet中
     *
     * @param tableName
     * @return
     */
    private ResultSet getDataResultSet(String tableName) {
        Connection connection = factory.openSession().getConnection();//获得数据库链接
        String sql = "SELECT  * FROM  " + tableName;
        ResultSet resultSet = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    /**
     * 数据库中对应表的列名称
     */
    private List getColName(String tableName) {
        ResultSet resultSet = getDataResultSet(tableName);
        ResultSetMetaData metaData = null;
        List list = new ArrayList();
        try {
            metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int i = 0; i < columnCount; i++) {
                list.add(metaData.getColumnName(i + 1).toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 获得数据库中对应表的列类型
     */
    private List getColTypeName(String tableName) {
        ResultSet resultSet = getDataResultSet(tableName);
        ResultSetMetaData metaData = null;
        List list = new ArrayList();
        try {
            metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int i = 0; i < columnCount; i++) {
                list.add(metaData.getColumnTypeName(i + 1).toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 生成excel表格并保存至本地
     *
     * @param tableName 要导出的数据库表名称
     * @param path      需要生成excel的地址
     */

    public void BuildExcel(String tableName, String path) {
        List colNameList = getColName(tableName);
        SXSSFWorkbook workbook = new SXSSFWorkbook();
        Sheet sheet = workbook.createSheet(tableName);
        sheet.setDefaultColumnWidth(15);//设置表格的默认列宽

        XSSFCellStyle cellStyle = (XSSFCellStyle) workbook.createCellStyle();//设置表头单元格的样式
        cellStyle.setAlignment(HorizontalAlignment.CENTER);//文字居中
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, colNameList.size() - 1));//表头跨列

        XSSFFont font = (XSSFFont) workbook.createFont();//创建字体样式
        font.setColor(new XSSFColor(Color.red));
        font.setFamily(FontFamily.ROMAN);
        cellStyle.setFont(font);

        Row row = sheet.createRow(0);
        Cell cell1 = row.createCell(0);
        cell1.setCellStyle(cellStyle);//将单元格样式应用于单元格
        cell1.setCellValue("这是关于" + tableName + "的表格");

        //设置列名的样式
        XSSFCellStyle cellStyle1 = (XSSFCellStyle) workbook.createCellStyle();
        cellStyle1.setAlignment(HorizontalAlignment.CENTER);
        XSSFFont font1 = (XSSFFont) workbook.createFont();
        font1.setColor(new XSSFColor(Color.green));
        font1.setFamily(FontFamily.ROMAN);
        cellStyle1.setFont(font1);
        //设置单元格的样式
        XSSFCellStyle cellStyle2 = (XSSFCellStyle) workbook.createCellStyle();
        cellStyle2.setAlignment(HorizontalAlignment.CENTER);
        XSSFFont font2 = (XSSFFont) workbook.createFont();
        font2.setColor(new XSSFColor(Color.pink));
        font2.setFamily(FontFamily.ROMAN);
        cellStyle2.setFont(font2);

        //设置表格的列名称
        Row row1 = sheet.createRow(1);
        for (int i = 0; i < colNameList.size(); i++) {
            Cell cell = row1.createCell(i);
            cell.setCellStyle(cellStyle1);
            cell.setCellValue(colNameList.get(i).toString());
        }
        ResultSet resultSet = getDataResultSet(tableName);
        //将正式数据插入到excel表格中
        try {
            int rowNum = 2;
            while (resultSet.next()) {
                Row row2 = sheet.createRow(rowNum);
                for (int j = 0; j < colNameList.size(); j++) {
                    Cell cell = row2.createCell(j);
                    cell.setCellStyle(cellStyle2);
                    cell.setCellValue(resultSet.getObject(j + 1) == null ?
                            "" : resultSet.getObject(j + 1).toString());
                }
                rowNum++;
            }
            workbook.write(new FileOutputStream(new File(path)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 将指定位置的excel导入到tableName所对应的数据库表中
     *
     * @param filePath  excel所存放的物理地址
     * @param tableName 数据库中对应的表名称
     */
    public void importDataToDB(String tableName, String filePath) {
        List colNameList = getColName(tableName);
        List colTypeList = getColTypeName(tableName);
        try {
            XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(new File(filePath)));
//          SXSSFWorkbook workbook = new SXSSFWorkbook(wb);
            Sheet sheet = wb.getSheetAt(0);
            Row row1 = sheet.getRow(2);
            int maxRowNum = sheet.getLastRowNum();//表格的总行数
            int lastCellNum = row1.getLastCellNum();//表格的总列数

            for (int i = 2; i < maxRowNum + 1; i++) {
                Row row = sheet.getRow(i);
                StringBuffer sql = new StringBuffer("(");//需要插入的数据的sql
                StringBuffer colNameSql=new StringBuffer("(");//插入数据所对应的列名称
                for (int j = 0; j < lastCellNum; j++) {
                    row.getCell(j).setCellType(Cell.CELL_TYPE_STRING);//设置excel表格的类型为字符串
                    String stringCellValue = row.getCell(j).getStringCellValue() == null
                            ||  row.getCell(j).getStringCellValue().length()==0?
                            "\'\'" : row.getCell(j).getStringCellValue();
                    String colName=colNameList.get(j).toString();
                    String typeLow = colTypeList.get(j).toString().toLowerCase();
                    switch (typeLow) {
                        case "varchar2":
                            stringCellValue = "\'" + stringCellValue + "\'";
                            break;
                        case "number":
                            break;
                        case "date":
                            stringCellValue="to_date(substr('"+stringCellValue+"',0,19),'yyyy-mm-dd hh24:mi:ss')";
                            break;
                        default:
                            break;
                    }

                    if (j == lastCellNum - 1) {
                        sql.append(stringCellValue).append(")");
                        colNameSql.append(colName).append(")");
                    } else {
                        sql.append(stringCellValue).append(",");
                        colNameSql.append(colName).append(",");
                    }
                }
                StringBuffer sqlEnd=new StringBuffer();
                sqlEnd.append("insert into "+tableName+colNameSql.toString()+" values "+sql.toString());
                dao.insertBySql(sqlEnd.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("系统未找到指定文件");
        }
    }


    public static void main(String[] args) {
        ApplicationContext wtx = new ClassPathXmlApplicationContext("applicationContext.xml");
        ExportData exportData = (ExportData) wtx.getBean("exportData");
        exportData.BuildExcel("PRACTISE", "f://persons.xlsx");
    }
}
