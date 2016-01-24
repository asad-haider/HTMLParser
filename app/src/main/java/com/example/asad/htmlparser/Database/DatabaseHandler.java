package com.example.asad.htmlparser.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Asad on 8/10/2015.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "resultapp.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "ridsinfo";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_RIDS = "rid";
    public static final String COLUMN_TEXT = "text";

    public static final String TEXT = " TEXT ";
    public static final String INTEGER = " INTEGER ";
    public static final String COMMA = ", ";

    String[] rids = {"SSC Part 1 General Group 2007 Karachi", "HSC Part II Pre-Medical 2007 Karachi", "HSC Part II Science General 2007 Karachi", "HSC Part II Pre-Engineering 2007 Karachi", "HSC Part II Commerce 2007 Karachi", "SSC Part 1 Science Group 2007 Karachi", "HSC Part II Arts/Humanities 2007 Karachi", "HSC Part I Science General 2007 Karachi", "HSC Part I Pre-Engineering 2007 Karachi", "HSC Part I Pre-Medical 2007 Karachi", "HSC Part I Commerce 2007 Karachi", "HSC Part I Arts/Humanities (Private) 2007 Karachi", "SSC Part II Science & General Supplementry 2007 Karachi", "HSC Part I Arts/Humanities (Regular) 2007 Karachi", "HSC Part II Pre-Medical (Supp) 2007 Karachi", "HSC Part II Pre-Engineering (Supp) 2007 Karachi", "HSC Part II Science General (Supp) 2007 Karachi", "HSC Part II Commerce Reg/Pvt (Supp) 2007 Karachi", "HSC Part II Humanities Pvt (Supp) 2007 Karachi", "HSC Part II Humanities Reg (Supp) 2007 Karachi", "DMC Mbbs / Bds Entrance Test 2008", "HSC part-II Home Economics 2008 Karachi", "SSC Part-II General 2008 Karachi", "KU BA Part I External Annual Examination 2007", "KU B.COM PART-II/BOTH PARTS (EXTERNAL) Annual Examinations 2007", "KU BSc(Pass) Part II Both Parts Annual Examination 2007", "DMC & SMC MBBS 2nd Year Semester-III (2008)", "KU B.A (PART-II) Regular Annual Examination 2007", "SSC Part II Science Annual 2008 Karachi", "DMC & SMC MBBS 4th Year Semester-VII (2008)", "DMC & SMC MBBS 3rd Year Semester-V (2008)", "NULL", "SSC-Annual Examination 2008 Rawalpindi", "KU B.A (PART-II & Both Parts) External Annual Examination 2007", "SSC-Annual Examination 2008 Multan", "HSC part-I Home Economics 2008 Karachi", "HSC Part II Annual Examination 2008 Fedral Board", "HSC Part I Annual Examination 2008 Fedral Board", "HSC Part I Annual Examination 2008 Rawalpindi Board", "NULL", "Intermediate (Part-II) Annual Examination, 2008 Gujranwala Board", "Intermediate Part II & combined Annual Examination 2008 Bahawalpur Board", "Intermediate (Part-II) Annual Examination, 2008 Sargodha Board", "Intermediate (Part-II) Annual Examination, 2008 Multan Board", "SSC Part I Annual Examination 2008 Rawalpindi", "NULL", "Sindh University B.A (PART-II) 2007", "SSC Part I (9th) Annual Examination 2008 Gujranwala", "SSC Part I (9th) Annual Examination 2008 Bahawalpur", "SSC Part I (9th) Annual Examination 2008 Sargodha", "HSC Part II Pre-Medical 2008 Karachi", "Sindh University BSC (PART-II)", "DAE 3rd Year 1st Annual Examination 2008 PBTE", "HSC Part II Science General Group 2008 Karachi", "HSC Part II Pre-Engineering Group 2008 Karachi", "DAE 1st & 2nd Year Annual Examination 2008 PBTE", "B.A./B.Sc. Annual Examination 2008 Punjab University", "CAP Computer Science (Male) 2008", "CAP Computer Science (Female) 2008", "CAP Home Economics (Female) 2008", "CAP Pre Engineering (Male) 2008", "CAP Pre Medical (Female) 2008", "CAP Pre Engineering (Female) 2008", "CAP Pre Medical (Male) 2008", "NULL", "Inter Part 1- 2008 Rawalpindi", "HSC Part II Commerce Group 2008 Karachi", "NULL", "B.Sc. Home Economics 1st Year, Annual Examination 2008 Punjab University", "B.Sc. Home Economics 3rd Year, Annual Examination 2008 Punjab University", "CAP Commerce (Male) 2008", "CAP Commerce (Female) 2008", "SSC Part 1 General Group 2008 Karachi", "CAP Humanities (Male) 2008", "CAP Humanities (Female) 2008", "M.A (Elementary) Part-I, Annual Examination 2007 Punjab University", "B.Com Part-II Annual Examination 2008 Punjab University", "B.A./B.Sc. Annual Examination 2008 Punjab University", "SSC Part 1 (9th) Science Group 2008 Karachi", "HSC Part II Arts/Humanities Reg/Pvt/Special 2008 Karachi", "HSC Part I Pre-Engineering 2008 Karachi", "HSC Part I Science General 2008 Karachi", "HSC Part I Pre-Medical 2008 Karachi", "SSC Supplementary Examination 2008 Fedral Board", "SSC Supplementary Examination 2008 Rawalpindi Board", "NULL", "HSC Part I Commerce Groups 2008 (Reg/Pvt) Karachi", "HSSC Supplementary Result 2008 Fedral Board", "SSC Part II General Group (Supp) 2008 Karachi", "SSC Part II Science Group (Supp) 2008 Karachi", "HSC Part I Arts/Humanities Pvt 2008 Karachi", "HSC Part I Arts/Humanities Reg 2008 Karachi", "HSC Part II Pre-Engineering (Supp) 2008 Karachi", "HSC Part II Pre-Medical (Supp) 2008 Karachi", "HSC Part II Science General (Supp) 2008 Karachi", "HSC Part II Homeconomics (Supp) 2008 Karachi", "HSC Part II Humanities (Supp) Reg 2008 Karachi", "NULL", "HSC Part II Humanities (Supp) Pvt 2008 Karachi", "HSC Part II Commerce (Supp) Reg/Pvt 2008 Karachi", "NULL", "KU BCom PART- I (Reg) Annual Examination 2008", "KU BCom PART- I (Extertnal) Annual Examination 2008", "KU BCom Part-II / Both Parts (Reg) Annual Examination 2008", "B.A - I I & Both Parts Regular Annual Examination 2008", "M.B.B.S. Final Professional, Annual Examination 2009", "SSC Part-II General Group 2009 (Reg / Pvt) Karachi", "SSC Part-II 2009 Federal Board", "SSC Composite 2009 Federal Board", "KU B.A. Part- II & Both Parts (External) Annual Examination 2008", "SSC Part-I 2009 Federal Board", "BSc Part-II/Both Regular Annual Examination 2008", "BSC Part-I Regular Annual Examination 2008", "SSC Part-II Science Group Annual Examination 2009 Karachi", "HSC Part-II Home Economics Group (Annual) 2009", "B.Com. Part- II & Both Parts (External) Annual Examination 2008", "Federal Board - Annual Result HSC Part II 2009", "B.A (PART-I) Regular Annual Examination 2008", "SSC (Matric) Annual Examination 2009 Lahore", "HSC Part II Pre-Medical 2009 Karachi", "PBTE Diploma in Commerce & Business Administration 1st Annual Examinations 2009", "SSC Part-I General Group 2009 (Reg / Pvt) Karachi", "NULL", "NULL", "HSC Part II General Group 2009 Karachi", "HSC Part II Pre-Engineering Group 2009 Karachi", "NULL", "CAP Computer Science (Female) 2009", "CAP Computer Science (Male) 2009", "CAP Home Economics (Female) 2009", "CAP Pre - Engineering (Male) 2009", "CAP Pre Medical (Female) 2009", "CAP Pre Medical (Male) 2009", "NULL", "HSSC-Annual Examination Part 1, 2009 Rawalpindi Board", "CAP Pre Engineering (Female) 2009", "CAP Commerce (Male) 2009", "CAP Commerce (Female) 2009", "CAP Humanities (Female) 2009", "CAP Humanities (Male) 2009", "HSC Part II Commerce Reg/Pvt 2009 Karachi", "SSC Part 1 Science Group 2009 Karachi", "HSC Part II Arts/Humanities (Regular / Special) 2009 Karachi", "HSC Part II Arts/Humanities (Private) 2009 Karachi", "KU BCom PART- I (Reg/Ext) Supplementary Examination 2008", "HSC Part I Pre-Medical 2009 Karachi", "HSC Part I Home Economics 2009 Karachi", "HSC Part I Science General 2009 Karachi", "HSC Part I Pre-Engineering 2009 Karachi", "SSC (Matric) Supplementary Examination, 2009 Lahore", "KU B.Com Part- II & Both Parts (External) Supplementary Examination 2008", "KU B.Com Part- II & Both Parts (Regular) Supplementary Examination 2008", "HSC Part I Arts/Humanities (Special) 2009 Karachi", "HSC Part I Arts/Humanities (Reg and Pvt) 2009 Karachi", "NULL", "SSC Part II General Group (Supp) 2009 Karachi", "SSC Part II Science Group (Supp) 2009 Karachi", "HSC Part I Commerce (Reg and Pvt) 2009 Karachi", "PBTE Diploma in Commerce & Business Administration 2nd Annual Examinations 2009", "PBTE DAE 2nd Annual Examinations 2009", "INTERMEDIATE SCIENCE (PRE-MEDICAL) GROUP PART - II SUPPLEMENTARY EXAMINATION 2009", "HSC Part II Pre-Engineering Supplementary Examination 2009", "HSC Part II Science General Supplementary Examination 2009", "HSC Part - II Humanities (Reg & Pvt ) Supplementary Examination 2009", "HSC Part - II Supplementary (Reg & Pvt) Supplementary Examination 2009", "KU B.Com Part- II & Both Parts (External) Annual Exam 2009", "SSC Part-II Annual Result 2010 Federal Board", "SSC Part-II General 2010 Karachi", "B.COM. PART- I ( Regular ) Annual Examination 2009", "Federal Board - SSC Part I Annual Result 2010", "SSC Part-II Science Annual Examination 2010 Karachi", "HSC Part-II Home Economics Group (Annual) 2010", "B.COM. PART- I ( External ) Annual Examination 2009", "PBTE Diploma in Commerce (Part-I) 2010", "NULL", "HSSC Part II Annual Result 2010, Federal Board", "HSC Part II Pre-Medical 2010 Karachi", "FBISE - HSSC Part-I Annual Result 2010", "BA Part-II / Both (Reg) Annual Exam 2009", "HSC Part II Science General 2010 Karachi", "HSC Part II Pre-Engineering 2010 Karachi", "Rawalpindi Board SSC-Annual Examination 2010 Part A (9th Class)", "NULL", "BA (Part-I) Regular Annual Examination KU, 2009", "HSC Part I Home Economics 2010 Karachi", "NULL", "CAP Computer Science (Female) 2010", "CAP Computer Science (Male) 2010", "CAP Home Economics (Female) 2010", "BA Part-II / Both (Ext) Annual Examination, 2009", "CAP Pre Engineering (Male) 2010", "CAP Pre Medical (Male) 2010", "CAP Pre Medical (Female) 2010", "CAP Pre Engineering (Female) 2010", "CAP Commerce (Male) 2010", "NULL", "SSC Part-I General Group 2010 (Reg / Pvt) Karachi", "CAP Commerce (Female) 2010", "CAP Humanities (Male) 2010", "CAP Humanities (Female) 2010", "HSSC Part 1- Annual Examination Rawalpindi 2010", "BISE Lahore HSSC Part 1- Annual Examination 2010", "HSC Part II Commerce (Reg/Pvt) 2010 Karachi", "DMC NTS Test 2010 Result Merit Wise", "SSC Part 1 Science Group 2010 Karachi", "HSC Part II Arts/Humanities (Reg / Pvt) 2010 Karachi", "FBISE - SSC Supplementary Examination 2010", "HSC Part I Pre-Medical 2010 Karachi", "HSC Part I Science General 2010 Karachi", "HSC Part I Pre-Engineering 2010 Karachi", "HSC Part I Humanities (Reg/Pvt) 2010 Karachi", "HSC Part I Commerce (Reg/Pvt) 2010 Karachi", "NULL", "SSC Part II Science & General Group (Supp) 2010 Karachi", "HSC Part II Pre-Medical (Supp) 2010 Karachi", "HSC Part II Pre-Engineering (Supp) 2010 Karachi", "HSC Part II Science General (Supp) 2010 Karachi", "HSC Part II Commerce Reg/Pvt (Supp) 2010 Karachi", "HSC Part II Humanities Reg/Pvt (Supp) 2010 Karachi", "BCom PART-II / Both (Reg) Annual Examination 2010", "B.Sc. (Pass) Part-II Both Parts Annual Examination 2010", "B.A. Part- II & Both Parts (Regular) Annual Examination 2010", "B.A. Part- II & Both Parts (External) Annual Examination 2010", "B. Sc.(Pass) Part-I Annual Examination 2010", "SSC Part-II General (Reg/Pvt) 2011 Karachi", "PBTE Martic Tech 1st Annual Examination 2011", "B.Com Part-II & Both External Annual Examination 2010", "SSC Part-II Science (Reg/Pvt) 2011 Karachi", "B.A (Part-I) Regular Annual Examination 2010", "NULL", "NULL", "HSC Part II Home Economics Annual Examinations 2011 Karachi", "SSC Part-I General Group 2011 (Reg / Pvt) Karachi", "FBISE HSSC Part-I Annual Examination 2011", "NULL", "HSC Part II Pre-Medical Annual Examinations 2011 Karachi", "HSC Part II Science General Annual Examinations 2011 Karachi", "HSC Part II Pre Engineering Annual Examinations 2011 Karachi", "CAP Pre Engineering (Male) 2011", "CAP Home Economics (Female) 2011", "CAP Computer Science (Female) 2011", "CAP Computer Science (Male) 2011", "CAP Pre Medical (Male) 2011", "CAP Pre Engineering (Female) 2011", "CAP Commerce (Male) 2011", "CAP Pre Medical (Female) 2011", "CAP Commerce (Female) 2011", "PBTE DAE / DDM First Annual Examination 2011", "CAP Humanities (Male) 2011", "CAP Humanities (Female) 2011", "HSC Part II Commerce Annual Examinations 2011 Karachi", "SSC Part 1 Science Group 2011 Karachi", "HSC Part II Commerce (Pvt) Annual Examinations 2011 Karachi", "HSC Part II Humanities (Reg/Pvt) Annual Examinations 2011 Karachi", "BISE Lahore HSSC Part 1- Annual Examination 2011", "HSC Part I Pre-Medical 2011 Karachi", "HSC Part I Pre-Engineering & Science General 2011 Karachi", "SSC Part II Science & General Group (Supp) 2011 Karachi", "NULL", "HSC Part I Humanities 2011 Karachi - Regular & Special Students", "HSC Part I Humanities 2011 Karachi - Private", "HSC Part I Commerce 2011 Karachi - Private", "HSC Part I Commerce 2011 Karachi - Regular", "HSC Part II Pre-Medical (Supp) 2011 Karachi", "HSC Part II Commerce - Pvt (Supp) 2011 Karachi", "HSC Part II Pre-Engineering (Supp) 2011 Karachi", "HSC Part II Science General (Supp) 2011 Karachi", "HSC Part II Humanities - Reg (Supp) 2011 Karachi", "HSC Part II Commerce - Reg (Supp) 2011 Karachi", "HSC Part II Humanities - Pvt (Supp) 2011 Karachi", "B.Com. Part I (Regular) Annual Examination 2011 Karachi", "B.Com. Part-II / Both (Reg) 2011 Karachi", "SSC Part-II General (Reg/Pvt) 2012 Karachi", "B.Sc. (Pass) Part-II/Both Exam 2011 Karachi", "FBISE - SSC-II Annual Examination 2012", "FBISE - SSC-I Annual Examination 2012", "NULL", "SSC Part-II Science (Reg/Pvt) 2012 Karachi", "NULL", "HSC Part-II Home Economics 2012 Karachi", "B.A Part II & Both Parts Regular 2011 Karachi", "HSC Part II Pre-Medical Annual Examinations 2012 Karachi", "B.Com. Part- I (External) Annual Examination 2011 Karachi", "HSC Part - I Home Economics Annual 2012 Karachi", "MBBS. 3rd Professional, Annual Examination 2012 Karachi", "HSC Part II Science General Annual Examinations 2012 Karachi", "HSC Part II Pre Engineering Annual Examinations 2012 Karachi", "NULL", "HSSC-Annual Examination 2012, Bahwalpur", "HSSC-Annual Examination 2012, Gujaranwala", "HSSC-Annual Examination 2012, Rawalpindi", "CAP Computer Science (Female) 2012", "CAP Computer Science (Male) 2012", "CAP Pre Engineering (Male) 2012", "CAP Pre Medical (Female), 2012", "CAP Pre Medical (Male) 2012", "CAP Pre Engineering (Female) 2012", "CAP Commerce (Male) 2012", "CAP Commerce (Female) 2012", "CAP Humanities (Female)", "CAP Humanities (Male)", "HSC Part II Commerce Annual Examinations 2012 Karachi", "HSSC-Part I Examination 2012, Bahwalpur", "HSSC-Part I Examination 2012, Gujaranwala", "HSSC-Part I Examination 2012, Rawalpindi", "NULL", "HSC Part II Humanities (Reg/Pvt) Annual Examinations 2012 Karachi", "HSC Part II Commerce (Pvt) Annual Examinations 2012 Karachi", "SSC Part 1 General Group 2012 Karachi", "SSC Part 1 Science 2012 Karachi", "HSC Part I Pre-Medical 2012 Karachi", "SSC Part II Science & General Group (Supp) 2012 Karachi", "HSC Part I Pre Engineering & Science General 2012 Karachi", "HSC Part I Humanities (Reg/Pvt) 2012 Karachi", "NULL", "SSC (Matric) Supplementary Examination, 2012 Rawalpindi", "SSC (Matric) Supplementary Examination, 2012 Gujranwala", "HSC Part I Commerce (Reg/Pvt) 2012 Karachi", "Inter Part II Commerce Regular / Private (SUPPLEMENTARY) 2012 Karachi", "Inter PART-II Pre-Engineering (SUPPLEMENTARY) 2012 Karachi", "Inter PART-II Pre-Medical (SUPPLEMENTARY) 2012 Karachi", "Inter PART-II HUMANITIES Regular / Private (SUPPLEMENTARY) 2012 Karachi", "Inter Part II Science General (SUPPLEMENTARY) 2012 Karachi", "Inter Part II HOME ECONOMICS (SUPPLEMENTARY) 2012 Karachi", "B.A (PART-I) Regular & Private, Annual Examination 2012", "NULL", "NULL", "NULL", "NULL", "NULL", "NULL", "NULL", "NULL", "NULL", "NULL", "NULL", "NULL", "NULL", "SSC (Matric) Supplementary Examination, 2012 Abbottabad", "KU B.Com. PART- II & Both Parts (Reg) Annual Examination 2012", "SSC-II Annual Examination (2013) - Federal Board", "SSC-I Annual Examination (2013) - Federal Board", "SSC Part II General Group 2013 Karachi", "NULL", "Rawalpindi Board SSC-Part 2 (10th Class) 2013", "Gujranwala Board SSC-Part 2 (10th Class) 2013", "Sargodha Board SSC-Part 2 (10th Class) 2013", "Bahawalpur Board SSC-Part 2 (10th Class) 2013", "D.G.Khan Board SSC-Part 2 (10th Class) 2013", "Multan Board SSC-Part 2 (10th Class) 2013", "Faislabad Board SSC-Part 2 (10th Class) 2013", "SSC Part II Science 2013 Karachi", "B.A. Part- II & Both Parts (Regular-External) Annual Examination 2012", "BA/BSC Results 2013 Sargodha University", "NULL", "Bahawalpur Board SSC-Part I (9th Class) 2013", "Sargodha Board SSC-Part I (9th Class) 2013", "Faislabad Board SSC-Part I (9th Class) 2013", "Rawalpindi Board SSC-Part I (9th Class) 2013", "Punjab University B.A. / B.Sc. Annual Examination 2013", "D.G.Khan Board SSC-Part I (9th Class) 2013", "Gujranwala Board SSC-Part I (9th Class) 2013", "FBISE HSSC-II ANNUAL EXAMINATION (2013)", "HSC Part II Pre-Medical Annual Examinations 2013 Karachi", "HSC Part II Home-Economics Annual Examinations 2013 Karachi", "FBISE HSSC-I ANNUAL EXAMINATION (2013)", "Larkana Board HSSC II Pre-Medical & Pre-Engineering 2013", "Karakoram International University HSSC-II Annual Examinations 2013", "CAP Computer Science (Male & Female) 2013", "HSC Part II Pre-Engineering Annual Examinations 2013 Karachi", "HSC Part II Science General Annual Examinations 2013 Karachi", "HSC Part I Home Economics Annual Examinations 2013 Karachi", "CAP Pre Engineering - Female 2013", "CAP Pre Engineering - Male 2013", "CAP Pre Medical - Male 2013", "CAP Pre Medical - Female 2013", "CAP Humanities - Male 2013", "Punjab University B.Com Part-I Annual Examination 2013", "Punjab University B.Com Part-II Annual Examination 2013", "CAP Commerce - Male 2013", "CAP Humanities - Female 2013", "KU - B.Com. PART- I (External) Annual Examination 2012", "PBTE DAE, First Annual Examination 2013", "PBTE DDM, First Annual Examination 2013", "CAP Commerce - Female 2013", "NULL", "BISE Faisalabad HSSC II Annual Examination 2013", "BISE Bahawalpur HSSC II Annual Examination 2013", "BISE Sargodha HSSC II Annual Examination 2013", "BISE Gujranwala HSSC II Annual Examination 2013", "BISE AJK HSSC II Annual Examination 2013", "BISE Sahiwal HSSC II Annual Examination 2013", "HSC Part II Commerce 2013 Karachi", "HSC Part II Humanities (Reg & Special Candidates) 2013 Karachi", "BISE Faisalabad HSSC I Annual Examination 2013", "BISE Rawalpindi HSSC I Annual Examination 2013", "BISE Multan HSSC I Annual Examination 2013", "NULL", "HSC Part II Commerce (Private) 2013 Karachi", "HSC Part II Humanities (Private) 2013 Karachi", "SSC Part 1 General Group 2013 Karachi", "HSC Part I Humanities 2013 Karachi - Regular", "HSC Part I Pre-Medical 2013 Karachi", "HSC Part I Humanities 2013 Karachi - Private", "SSC Part I (9th) Science 2013 Karachi", "NULL", "Faisalabad Board SSC-Part II Supplementary 2013", "DG Khan Board SSC-Part II Supplementary 2013", "Gujranwala Board SSC-Part II Supplementary 2013", "Bahawalpur Board SSC-Part II Supplementary 2013", "Sargodha Board SSC-Part II Supplementary 2013", "Multan Board SSC-Part II Supplementary 2013", "HSC Part I Commerce (Pvt) 2013 Karachi", "HSC Part I Pre-Engineering & Sci General 2013 Karachi", "HSC Part I Commerce (Reg) 2013 Karachi", "SSC Part II Science & General Group (Supp) 2013 Karachi", "Gujranwala Board HSSC Supplementary 2013", "NULL", "Rawalpindi Board HSSC Supplementary 2013", "DG Khan Board HSSC Supplementary 2013", "AJK Board HSSC Supplementary 2013", "HSC Part II Humanities (Supp) 2013 Karachi", "HSC Part II Commerce (Supp) 2013 Karachi", "HSC Part II Pre-Engineering & Sci General (Supp) 2013 Karachi", "HSC Part II Pre-Medical (Supp) 2013 Karachi", "KU B.A (PART-I) External, Annual Examination 2013", "KU B.A (PART-I) Regular, Annual Examination 2013", "KU B.A. Part-II & Both Parts (Regular) Annual Examination 2013", "BISE Malakand E-DMC SSC 10th Annual 2014", "BISE Malakand SSC 9th - Annual -2014", "BISEB Bannu SSC 9th Result 2014", "FBISE SSC-II Annual Examination 2014", "FBISE SSC-I Annual Examination 2014", "BISEH SSC-II Annual Examination 2014", "SSC Part II (10th) Science 2014 Karachi", "NULL", "SSC Part II (10th) General 2014 Karachi", "FBISE HSSC-II Annual Examination 2014", "HSC Part II Pre-Medical 2014 Karachi", "HSC Part II Medical Technology 2014 Karachi", "HSC Part II Home Economics 2014 Karachi", "FBISE HSSC-I Annual Examination 2014", "PU B.A. / B.Sc. Annual Examination 2014", "BISE SSC-I Annual Examination 2014 Sargodha", "BISE SSC-I Annual Examination 2014 Faislabad", "BISE SSC-I Annual Examination 2014 Lahore", "BISE SSC-I Annual Examination 2014 Gujranwala", "BISE SSC-I Annual Examination 2014 Rawalpindi", "BIEK HSC Part II Pre-Engineering & Science General 2014 Karachi", "KU B.Com Part II & Both Parts (External) Annual Examination 2013", "HSC Part II Commerce (Reg/Pvt) 2014 Karachi", "BISE HSSC-II Annual Examination 2014 Gujranwala", "BISE HSSC-II Annual Examination 2014 Rawalpindi", "BISE HSSC-II Annual Examination 2014 Sargodha", "BISE HSSC-II Annual Examination 2014 Multan", "NULL", "BISE HSSC-II Annual Examination 2014 Lahore", "BISE HSSC-II Annual Examination 2014 Bahawalpur", "BISE HSSC-II Annual Examination 2014 AJK", "HSC Part II Humanities (Reg/Pvt) 2014 Karachi", "BISE HSSC- I Annual Examination 2014 AJK", "BISE HSSC-I Annual Examination 2014 Bahawalpur", "BISE HSSC-I Annual Examination 2014 Sargodha", "BISE HSSC-I Annual Examination 2014 Gujranwala", "BISE HSSC-I Annual Examination 2014 Rawalpindi", "BISE HSSC-I Annual Examination 2014 DG Khan", "BISE HSSC-I Annual Examination 2014 Multan", "HSC Part - I Home Economics Annual 2014 Karachi", "HSC Part - I Commerce (Private) Annual 2014 Karachi", "HSC Part I Pre-Medical 2014 Karachi", "HSC Part I Humanities (Reg/Pvt) 2014 Karachi", "SSC Part 1 General Group 2014 Karachi", "HSC Part I Science General 2014 Karachi", "HSC Part I Pre-Engineering 2014 Karachi", "SSC Part I (9th) Science 2014 Karachi", "HSC Part I Commerce (Reg) 2014 Karachi", "HSC Part II Humanities (Supp) 2014 Karachi", "HSC Part II Home Economics (Supp) 2014 Karachi", "HSC Part II Pre-Engineering (Supp) 2014 Karachi", "HSC Part II Commerce (Supp) 2014 Karachi", "HSC Part II Sci General (Supp) 2014 Karachi", "HSC Part II Pre-Medical (Supp) 2014 Karachi", "SSC Part II Science & General Group (Supp) 2014 Karachi", "NULL", "KU B.Com. PART- I, (External) Annual Examination 2014", "KU B.Com. PART- II & Both Parts (External) Annual Examination 2014", "BISESS SSC Part II (Metric) 2015 Swat", "HSC Part II Humanities (Reg / Pvt) 2015 Karachi", "HSC Part II Home Economics 2015 Karachi", "Diploma In Physical Educaiton Annual Examinations 2015 Karachi", "HSC Part II Humanities (Special Candidates) 2015 Karachi", "FBISE SSC-II Annual Examination 2015", "FBISE SSC-I Annual Examination 2015", "KU B.A (PART-I) Regular & External, Annual Examination 2014", "BISERWP SSC-II Annual Examination 2015", "BISEGRW SSC-II Annual Examination 2015", "BISE Sargodha SSC-II Annual Examination 2015", "BISE Multan SSC-II Annual Examination 2015", "BISE Lahore SSC-II Annual Examination 2015", "SSC Part II (10th) Science 2015 Karachi", "SSC Part II (10th) General 2015 Karachi"}; // , "SSC II (Failed) Science & General 2015 Karachi"
    // , "SSC II (Failed) Science & General 2015 Karachi"

    private ArrayList<String> ridsArray;

    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID + INTEGER + "PRIMARY KEY AUTOINCREMENT" + COMMA +
                COLUMN_RIDS + TEXT + COMMA +
                COLUMN_TEXT + TEXT + ");";

        db.execSQL(query);

        ridsArray = new ArrayList<>(Arrays.asList(rids));

        ContentValues values = new ContentValues();

        for (int i = 0; i < ridsArray.size(); i++){
            if (!ridsArray.get(i).equals("NULL")){
                values.put(COLUMN_RIDS, String.valueOf(i + 1));
                values.put(COLUMN_TEXT, ridsArray.get(i));
                db.insert(TABLE_NAME, null, values);
                values.clear();
                Log.d("Data Added", ridsArray.get(i) + ", RID: " + (i + 1));
            }
            else{
                Log.d("NULL", "NULL RID: " + (i + 1));
            }
        }

        Log.d("onCreate", "Database Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addRID(InfoHandler handler){

        ContentValues values = new ContentValues();

        values.put(COLUMN_RIDS, handler.getRid());
        values.put(COLUMN_TEXT, handler.getRidText());

        Log.d("Data Added", handler.getRid() + ", " + handler.getRidText());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_NAME, null, values);

        if (db.isOpen()){
            db.close();
        }
    }

    public InfoHandler getRID(String rid){

        String query =  "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_RIDS + " = \"" + rid + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        InfoHandler infoHandler = new InfoHandler();

        if (cursor.moveToFirst()){
            cursor.moveToFirst();
            infoHandler.setRid(cursor.getString(1));
            infoHandler.setRidText(cursor.getString(2));
            cursor.close();
        }else{
            infoHandler = null;
        }

        return infoHandler;
    }

    public List<ArrayList<String>> getCompleteData() {

        String query = "SELECT * FROM " + TABLE_NAME;
        int count = 0;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        ArrayList<String> rids = new ArrayList<>();
        ArrayList<String> text = new ArrayList<>();

        if(cursor.moveToFirst()) {
            do {
                rids.add(cursor.getString(1));
                text.add(cursor.getString(2));

            } while(cursor.moveToNext());

            if(cursor != null && !cursor.isClosed()){
                count = cursor.getCount();
                cursor.close();
            }
        }

        List<ArrayList<String>> mergeData = new ArrayList<>();

        mergeData.add(rids);
        mergeData.add(text);

        db.close();

        return mergeData;
    }

    public String getLastRID(){

        String selectQuery= "SELECT * FROM " + TABLE_NAME +" ORDER BY " + COLUMN_ID + " DESC LIMIT 1";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        String lastRID = "";

        if(cursor.moveToFirst())
            lastRID  =  cursor.getString( cursor.getColumnIndex(COLUMN_RIDS));

        cursor.close();

        db.close();

        return lastRID;
    }

    public String getLastRIDText(){
        String selectQuery= "SELECT * FROM " + TABLE_NAME +" ORDER BY " + COLUMN_ID + " DESC LIMIT 1";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        String lastRIDText = "";

        if(cursor.moveToFirst())
            lastRIDText =  cursor.getString( cursor.getColumnIndex(COLUMN_TEXT));

        cursor.close();

        db.close();

        return lastRIDText;
    }

}
