package com.digitalhomeland.inventorymanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.digitalhomeland.inventorymanager.models.Application;
import com.digitalhomeland.inventorymanager.models.Attendance;
import com.digitalhomeland.inventorymanager.models.EmpRoster;
import com.digitalhomeland.inventorymanager.models.Employee;
import com.digitalhomeland.inventorymanager.models.Notif;
import com.digitalhomeland.inventorymanager.models.StRoster;
import com.digitalhomeland.inventorymanager.models.Store;
import com.digitalhomeland.inventorymanager.models.TaskInst;
import com.digitalhomeland.inventorymanager.models.Taskd;
import com.digitalhomeland.inventorymanager.models.Tasko;
import com.digitalhomeland.inventorymanager.models.Taskw;
import com.digitalhomeland.inventorymanager.models.Team;
import com.digitalhomeland.inventorymanager.models.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prthma on 13-05-2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 48;

    // Database Name
    private static final String DATABASE_NAME = "employeeDB";

    // table name
    private static final String TABLE_NOTIFICATION = "notificationsTable";
    private static final String TABLE_ATTENDANCE = "attendanceTable";
    private static final String TABLE_APPLICATIONS = "applicationsTable";
    private static final String TABLE_USER = "userTable";
    private static final String TABLE_STORE = "storeTable";
    private static final String TABLE_EMPLOYEE = "employeeTable";
    private static final String TABLE_TASKD = "taskdTable";
    private static final String TABLE_TASKW = "taskwTable";
    private static final String TABLE_TASKO = "taskoTable";
    private static final String TABLE_STROSTER = "stRosterTable";
    private static final String TABLE_EMPROSTER = "empRosterTable";
    private static final String TABLE_TEAM = "teamTable";


    // Notifications Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NTYPE = "type";
    private static final String KEY_TITLE = "title";
    private static final String KEY_SUBJECT = "subject";
    private static final String KEY_DATE = "date";
    private static  final String KEY_NEID = "employeeId";
    private static  final String KEY_NSEQID = "seqId";

    // Teams Table Columns names
    private static final String KEY_TID = "id";
    private static final String KEY_TNAME = "name";
    private static final String KEY_TMANAGERID = "managerId";
    private static final String KEY_TMANAGERNAME = "managerName";


    //Attendance Table Columns
    private static final String KEY_VALUE = "value";
    private static final String KEY_DATEINST = "date";
    private static  final String KEY_AEMPID = "employeeId";

    //Application Table column
    private static final String KEY_AID = "id";
    private static final String KEY_ACCEPTANCE = "accepted";
    private static final String KEY_ATITLE = "title";
    private static final String KEY_ASUBJECT = "subject";
    private static final String KEY_ADATE = "date";
    private static  final String KEY_AEID = "employeeId";
    private static final String KEY_ASEQID = "seqId";

    //User Table column
    private static final String KEY_UFIRSTNAME = "firstname";
    private static final String KEY_UMIDDLENAME = "middlename";
    private static final String KEY_ULASTNAME = "lastname";
    private static final String KEY_UPHONE = "phone";
    private static final String KEY_UEMAIL = "email";
    private static final String KEY_UAADHARID = "aadharid";
    private static final String KEY_UEID = "employeeid";
    private static final String KEY_USTOREID = "storeid";

    //Store Table column
    private static final String KEY_SID = "id";
    private static final String KEY_SNAME = "name";
    private static final String KEY_SSTOREID = "storeid";
    private static final String KEY_SCITY = "city";
    private static final String KEY_SSTATE = "state";
    private static final String KEY_SLAT = "latitude";
    private static final String KEY_SLONG = "longitude";
    private static final String KEY_SADDRESS = "address";
    private static final String KEY_SEMPCOUNT = "empCount";
    private static final String KEY_SKEYSTATE = "keyState";
    private static final String KEY_SSELLERID = "sellerId";
    private static final String KEY_SADMINID = "adminId";
    private static final String KEY_SCLOSINGDAY = "closingDay";
    private static final String KEY_SROSTERGENDAY = "rosterGenDay";


    //User Table column
    private static final String KEY_EFIRSTNAME = "firstname";
    private static final String KEY_EMIDDLENAME = "middlename";
    private static final String KEY_ELASTNAME = "lastname";
    private static final String KEY_EPHONE = "phone";
    private static final String KEY_EEMAIL = "email";
    private static final String KEY_EAADHARID = "aadharid";
    private static final String KEY_EEID = "employeeid";
    private static final String KEY_EISMANAGER = "ismanager";
    private static final String KEY_ETEAMNAME = "teamname";
    private static final String KEY_EDESIGNATION = "designation";
    private static final String KEY_EMANAGERID = "managerid";


    // Taskd Table Columns names
    private static final String KEY_DID = "id";
    private static final String KEY_DDATE = "date";
    private static final String KEY_DTITLE = "title";
    private static final String KEY_DSUBJECT = "subject";
    private static final String KEY_DEMPID = "empId";
    private static  final String KEY_DHOURS = "hours";
    private static  final String KEY_DMINS = "minutes";
    private static  final String KEY_DACCEPTEDAT = "acceptedAt";
    private static  final String KEY_DSEQID = "seqId";

    // Taskw Table Columns names
    private static final String KEY_WID = "id";
    private static final String KEY_WDATE = "date";
    private static final String KEY_WTITLE = "title";
    private static final String KEY_WSUBJECT = "subject";
    private static final String KEY_WEMPID = "empId";
    private static  final String KEY_WHOURS = "hours";
    private static  final String KEY_WMINS = "minutes";
    private static  final String KEY_WDAYOFWEEK = "dayOfWeek";
    private static  final String KEY_WACCEPTEDAT = "acceptedAt";
    private static  final String KEY_WSEQID = "seqId";

    // Taskw Table Columns names
    private static final String KEY_OID = "id";
    private static final String KEY_OTITLE = "title";
    private static final String KEY_OSUBJECT = "subject";
    private static final String KEY_OEMPID = "empId";
    private static  final String KEY_OHOURS = "hours";
    private static  final String KEY_OMINS = "minutes";
    private static  final String KEY_ODATETOSET = "dateToSet";
    private static  final String KEY_OACCEPTEDAT = "acceptedAt";
    private static  final String KEY_OSEQID = "seqId";

    // StStatus Table Columns names
    private static final String KEY_STRID = "id";
    private static final String KEY_STRDOFW = "dayOfW";
    private static final String KEY_STRDATE = "date";
    private static final String KEY_STRSTATUS = "storeStatus";
    private static  final String KEY_STREVENTS = "events";
    private static  final String KEY_STREVENTSTIT = "eventTitle";
    private static  final String KEY_STREVENTSUB = "eventSubject";
    private static  final String KEY_STRSEQID = "seqId";
    private static  final String KEY_STRPUSHED = "pushed";

    // empStatus Table Columns names
    private static final String KEY_EMRID = "id";
    private static final String KEY_EMRDOFW = "dayOfW";
    private static final String KEY_EMRDATE = "date";
    private static final String KEY_EMREMPID = "empId";
    private static final String KEY_EMRSTATUS = "empStatus";
    private static final String KEY_EMRSHIFT = "shift";
    private static  final String KEY_EMRPUSHED = "pushed";
    private static  final String KEY_EMRCHECKIN = "checkIn";
    private static  final String KEY_EMRCHECKOUT = "checkOut";

    String CREATE_NOTIFICATION_TABLE = "CREATE TABLE " + TABLE_NOTIFICATION + "("
            + KEY_ID + " TEXT,"
            + KEY_NTYPE + " TEXT," + KEY_TITLE + " TEXT," + KEY_SUBJECT + " TEXT," + KEY_NEID + " TEXT," + KEY_DATE + " TEXT," + KEY_NSEQID + " TEXT" + ")";
    String CREATE_ATTENDANCE_TABLE = "CREATE TABLE " + TABLE_ATTENDANCE + "("
            + KEY_VALUE + " TEXT," + KEY_DATEINST  + " TEXT," + KEY_AEMPID + " TEXT" + ")";
    String CREATE_TEAM_TABLE = "CREATE TABLE " + TABLE_TEAM + "("
            + KEY_TID + " TEXT," + KEY_TNAME  + " TEXT," + KEY_TMANAGERID  + " TEXT," + KEY_TMANAGERNAME + " TEXT" + ")";
    String CREATE_APPLICATIONS_TABLE = "CREATE TABLE " + TABLE_APPLICATIONS + "("
            + KEY_AID + " TEXT," + KEY_ACCEPTANCE + " TEXT,"
            + KEY_ATITLE + " TEXT," + KEY_ASUBJECT + " TEXT," + KEY_AEID + " TEXT," + KEY_ADATE + " TEXT," + KEY_ASEQID + " TEXT" + ")";
    String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + KEY_UFIRSTNAME + " TEXT," + KEY_UMIDDLENAME + " TEXT,"
            + KEY_ULASTNAME + " TEXT," + KEY_UPHONE + " TEXT," + KEY_UEMAIL + " TEXT," + KEY_UAADHARID + " TEXT," + KEY_UEID + " TEXT," + KEY_USTOREID + " TEXT" + ")";
    String CREATE_STORE_TABLE = "CREATE TABLE " + TABLE_STORE + "("
            + KEY_SID + " TEXT," + KEY_SNAME + " TEXT," + KEY_SSTOREID + " TEXT," + KEY_SEMPCOUNT + " TEXT," + KEY_SCITY + " TEXT," + KEY_SSTATE + " TEXT," + KEY_SLAT + " TEXT," + KEY_SLONG + " TEXT," + KEY_SADMINID + " TEXT," + KEY_SADDRESS + " TEXT," + KEY_SKEYSTATE + " TEXT," + KEY_SSELLERID + " TEXT," + KEY_SCLOSINGDAY + " TEXT," + KEY_SROSTERGENDAY + " TEXT " + ")";
    String CREATE_EMPLOYEE_TABLE = "CREATE TABLE " + TABLE_EMPLOYEE + "("
            + KEY_EFIRSTNAME + " TEXT," + KEY_EMIDDLENAME + " TEXT,"
            + KEY_ELASTNAME + " TEXT," + KEY_EPHONE + " TEXT," + KEY_EEMAIL + " TEXT," + KEY_EAADHARID + " TEXT," + KEY_EEID + " TEXT," + KEY_EISMANAGER + " TEXT," + KEY_ETEAMNAME + " TEXT," + KEY_EDESIGNATION + " TEXT," + KEY_EMANAGERID + " TEXT" + ")";
    String CREATE_TASKD_TABLE = "CREATE TABLE " + TABLE_TASKD + "("
            + KEY_DID + " TEXT,"
            + KEY_DTITLE + " TEXT," + KEY_DDATE + " TEXT," + KEY_DSUBJECT + " TEXT," + KEY_DEMPID + " TEXT," + KEY_DHOURS + " TEXT," + KEY_DMINS + " TEXT," + KEY_DACCEPTEDAT + " TEXT," + KEY_DSEQID + " TEXT" + ")";
    String CREATE_TASKW_TABLE = "CREATE TABLE " + TABLE_TASKW + "("
            + KEY_WID + " TEXT,"
            + KEY_WTITLE + " TEXT," + KEY_WDATE + " TEXT," + KEY_WSUBJECT + " TEXT," + KEY_WEMPID + " TEXT," + KEY_WHOURS + " TEXT," + KEY_WMINS + " TEXT," + KEY_WDAYOFWEEK + " TEXT," + KEY_WACCEPTEDAT + " TEXT," + KEY_WSEQID + " TEXT" + ")";
    String CREATE_TASKO_TABLE = "CREATE TABLE " + TABLE_TASKO + "("
            + KEY_OID + " TEXT,"
            + KEY_OTITLE + " TEXT," + KEY_OSUBJECT + " TEXT," + KEY_OEMPID + " TEXT," + KEY_OHOURS + " TEXT," + KEY_OMINS + " TEXT," + KEY_ODATETOSET + " TEXT," + KEY_OACCEPTEDAT + " TEXT," + KEY_OSEQID + " TEXT" + ")";
    String CREATE_STROSTER_TABLE = "CREATE TABLE " + TABLE_STROSTER + "("
            + KEY_STRID + " TEXT,"
            + KEY_STRDATE + " TEXT," + KEY_STRDOFW + " TEXT," + KEY_STRSTATUS + " TEXT," + KEY_STREVENTS + " TEXT," + KEY_STREVENTSTIT + " TEXT," + KEY_STREVENTSUB + " TEXT," + KEY_STRSEQID + " TEXT," + KEY_STRPUSHED + " TEXT" + ")";
    String CREATE_EMPROSTER_TABLE = "CREATE TABLE " + TABLE_EMPROSTER + "("
            + KEY_EMRID + " TEXT,"
            + KEY_EMRDATE + " TEXT," + KEY_EMREMPID + " TEXT," + KEY_EMRDOFW + " TEXT," + KEY_EMRSTATUS + " TEXT," + KEY_EMRSHIFT + " TEXT," + KEY_EMRPUSHED + " TEXT, " + KEY_EMRCHECKIN + " TEXT," + KEY_EMRCHECKOUT + " TEXT "  + ")";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        getWritableDatabase();
    }

    public DatabaseHandler(Context context, int version) {
        super(context, DATABASE_NAME, null, version);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
       try {
           db.execSQL(CREATE_NOTIFICATION_TABLE);
           Log.d("myTag", "created notif table");
           db.execSQL(CREATE_ATTENDANCE_TABLE);
           Log.d("myTag", "created attendance table");
           Log.d("myTag", "created resource table");
           db.execSQL(CREATE_APPLICATIONS_TABLE);
           db.execSQL(CREATE_USER_TABLE);
           db.execSQL(CREATE_STORE_TABLE);
           db.execSQL(CREATE_EMPLOYEE_TABLE);
           db.execSQL(CREATE_TASKD_TABLE);
           db.execSQL(CREATE_TASKW_TABLE);
           db.execSQL(CREATE_TASKO_TABLE);
           db.execSQL(CREATE_TEAM_TABLE);
           db.execSQL(CREATE_STROSTER_TABLE);
           db.execSQL(CREATE_EMPROSTER_TABLE);
       }catch(SQLException se){Log.d("myTag", "error in create", se);}
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTIFICATION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ATTENDANCE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_APPLICATIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STORE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EMPLOYEE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASKD);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASKW);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASKO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STROSTER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EMPROSTER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TEAM);
        // Create tables again
        onCreate(db);
    }


    // Upgrading database
    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */


    // Adding new notifications
    void addNotif(Notif notif) {
        try{
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, notif.getId()); // Contact Name
            values.put(KEY_NTYPE, notif.getType());
            values.put(KEY_TITLE, notif.getTitle()); // Contact Name
        values.put(KEY_SUBJECT, notif.getSubject()); // Contact Phone
        values.put(KEY_DATE, notif.getDate()); // Contact Name
        values.put(KEY_NEID, notif.getEmployeeId());
        values.put(KEY_NSEQID, notif.getSeqId());

        // Inserting Row

        long rowinsert =  db.insertOrThrow(TABLE_NOTIFICATION, null, values);
        Log.d("myTag" ,"rowinsert"+ rowinsert);
        db.close(); // Closing database connection
    }catch(Exception e){ Log.d("myTag", "err" + e.toString() + "\n" +  e.getMessage() + "\n" + e.getCause() + "\n" + e.getStackTrace() );
            Log.d("myTag", "err lol ", e);
    e.printStackTrace();
    }
    }

    // Adding new notifications
    void addAttendance(Attendance attendance) {
        try{
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(KEY_VALUE, attendance.getValue()); // Contact Phone
            values.put(KEY_DATEINST, attendance.getDate()); // Contact Name
            values.put(KEY_AEMPID , attendance.getEmployeeId());
            // Inserting Row

            long rowinsert =  db.insertOrThrow(TABLE_ATTENDANCE, null, values);
            Log.d("myTag" ,"rowinsert"+ rowinsert);
            db.close(); // Closing database connection
        }catch(Exception e){ Log.d("myTag", "err" + e.toString() + "\n" +  e.getMessage() + "\n" + e.getCause() + "\n" + e.getStackTrace() );
            Log.d("myTag", "err lol ", e);
            e.printStackTrace();
        }
    }

    // Adding new notifications
    void addTeam(Team team) {
        try{
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(KEY_TID, team.getId()); // Contact Phone
            values.put(KEY_TNAME, team.getName()); // Contact Name
            values.put(KEY_TMANAGERID , team.getManagerId());
            values.put(KEY_TMANAGERNAME , team.getManagerName());
            // Inserting Row
            long rowinsert =  db.insertOrThrow(TABLE_TEAM, null, values);
            Log.d("myTag" ,"rowinsert"+ rowinsert);
            db.close(); // Closing database connection
        }catch(Exception e){ Log.d("myTag", "err" + e.toString() + "\n" +  e.getMessage() + "\n" + e.getCause() + "\n" + e.getStackTrace() );
            Log.d("myTag", "err lol ", e);
            e.printStackTrace();
        }
    }

    // Adding new applications
    void addApplications(Application application) {
        try{
            SQLiteDatabase db = this.getWritableDatabase();
Log.d("myTag", "adding appli db : " + application.getSubject() + " : " + application.getDate() + " : " + application.getTitle());
            ContentValues values = new ContentValues();
            values.put(KEY_AID, application.getId()); // Contact Name
            values.put(KEY_ACCEPTANCE, application.getAcceptStatus()); // Contact Phone
            values.put(KEY_ATITLE, application.getTitle()); // Contact Name
            values.put(KEY_ASUBJECT, application.getSubject()); // Contact Phone
            values.put(KEY_ADATE, application.getDate()); // Contact Name
            values.put(KEY_AEID, application.getEmployeeId());
            // Inserting Row

            long rowinsert =  db.insertOrThrow(TABLE_APPLICATIONS, null, values);
            Log.d("myTag" ,"rowinsert"+ rowinsert);
            db.close(); // Closing database connection
        }catch(Exception e){ Log.d("myTag", "err" + e.toString() + "\n" +  e.getMessage() + "\n" + e.getCause() + "\n" + e.getStackTrace() );
            Log.d("myTag", "err lol ", e);
            e.printStackTrace();
        }
    }

    // Adding new user
    void addUsers(User user) {
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            Log.d("myTag", "adding user db : " + user.getFirstName() + " : " + user.getMiddleName() + " : " + user.getLastName() + user.getPhone() + " : " + user.getEmail() + " : " + user.getAadharId());
            ContentValues values = new ContentValues();
            values.put(KEY_UFIRSTNAME, user.getFirstName()); // Contact Name
            values.put(KEY_UMIDDLENAME, user.getMiddleName()); // Contact Phone
            values.put(KEY_ULASTNAME, user.getLastName()); // Contact Name
            values.put(KEY_UPHONE, user.getPhone()); // Contact Phone
            values.put(KEY_UEMAIL, user.getEmail()); // Contact Name
            values.put(KEY_UAADHARID, user.getAadharId());
            values.put(KEY_UEID, user.getEmployeeId());
            values.put(KEY_USTOREID, user.getStoreId());
            // Inserting Row

            long rowinsert =  db.insertOrThrow(TABLE_USER, null, values);
            Log.d("myTag" ,"rowinsert"+ rowinsert);
            db.close(); // Closing database connection
        }catch(Exception e){ Log.d("myTag", "err" + e.toString() + "\n" +  e.getMessage() + "\n" + e.getCause() + "\n" + e.getStackTrace() );
            Log.d("myTag", "err lol ", e);
            e.printStackTrace();
        }
    }

    void addEmployees(Employee employee) {
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            Log.d("myTag", "adding user db : " + employee.getFirstName() + " : " + employee.getMiddleName() + " : " + employee.getLastName() + employee.getPhone() + " : " + employee.getEmail() + " : " + employee.getAadharId());
            ContentValues values = new ContentValues();
            values.put(KEY_EFIRSTNAME, employee.getFirstName()); // Contact Name
            values.put(KEY_EMIDDLENAME, employee.getMiddleName()); // Contact Phone
            values.put(KEY_ELASTNAME, employee.getLastName()); // Contact Name
            values.put(KEY_EPHONE, employee.getPhone()); // Contact Phone
            values.put(KEY_EEMAIL, employee.getEmail()); // Contact Name
            values.put(KEY_EAADHARID, employee.getAadharId());
            values.put(KEY_EEID, employee.getEmployeeId());
            values.put(KEY_EISMANAGER, employee.getIsManager());
            values.put(KEY_ETEAMNAME, employee.getTeamName()); // Contact Name
            values.put(KEY_EDESIGNATION, employee.getDesignation());
            values.put(KEY_EMANAGERID, employee.getManagerId());
            // Inserting Row

            long rowinsert =  db.insertOrThrow(TABLE_EMPLOYEE, null, values);
            Log.d("myTag" ,"rowinsert"+ rowinsert);
            db.close(); // Closing database connection
        }catch(Exception e){ Log.d("myTag", "err" + e.toString() + "\n" +  e.getMessage() + "\n" + e.getCause() + "\n" + e.getStackTrace() );
            Log.d("myTag", "err lol ", e);
            e.printStackTrace();
        }
    }

    // Adding new user
    void addStores(Store store) {
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            //Log.d("myTag", "adding user db : " + school.getName() + " : " + school.getSchoolId() + " : " + school.getCity());
            ContentValues values = new ContentValues();
            values.put(KEY_SID, store.getId()); // Contact Name
            values.put(KEY_SNAME, store.getName()); // Contact Name
            values.put(KEY_SSTOREID, store.getStoreId()); // Contact Phone
            values.put(KEY_SCITY, store.getCity()); // Contact Name
            values.put(KEY_SSTATE, store.getState()); // Contact Name
            values.put(KEY_SLAT, store.getLat()); // Contact Name
            values.put(KEY_SLONG , store.getLongi()); // Contact Phone
            values.put(KEY_SADDRESS , store.getAddress()); // Contact Name
            values.put(KEY_SEMPCOUNT , store.getEmpCount()); // Contact Name
            values.put(KEY_SKEYSTATE , store.getKeyState());
            values.put(KEY_SADMINID , store.getAdminId());
            values.put(KEY_SCLOSINGDAY , store.getClosingDay());
            values.put(KEY_SROSTERGENDAY , store.getRosterGenDay());
            // Inserting Row

            long rowinsert =  db.insertOrThrow(TABLE_STORE, null, values);
            Log.d("myTag" ,"rowinsert"+ rowinsert);
            db.close(); // Closing database connection
        }catch(Exception e){ Log.d("myTag", "err" + e.toString() + "\n" +  e.getMessage() + "\n" + e.getCause() + "\n" + e.getStackTrace() );
            Log.d("myTag", "err lol ", e);
            e.printStackTrace();
        }
    }

    // Adding new notifications
    void addTaskd(Taskd tsk) {
        try{
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(KEY_DID, tsk.get_id()); // Contact Name
            values.put(KEY_DDATE, tsk.getDate());
            values.put(KEY_DEMPID, tsk.getEmpId()); // Contact Phone
            values.put(KEY_DTITLE, tsk.getTitle()); // Contact Name
            values.put(KEY_DSUBJECT, tsk.getSubject()); // Contact Phone
            values.put(KEY_DHOURS, tsk.getHours()); // Contact Name
            values.put(KEY_DMINS, tsk.getMinutes());
            values.put(KEY_DACCEPTEDAT, tsk.getAcceptedAt());
            values.put(KEY_DSEQID, tsk.getSeqId());

            // Inserting Row

            long rowinsert =  db.insertOrThrow(TABLE_TASKD, null, values);
            Log.d("myTag" ,"rowinsert"+ rowinsert);
            db.close(); // Closing database connection
        }catch(Exception e){ Log.d("myTag", "err" + e.toString() + "\n" +  e.getMessage() + "\n" + e.getCause() + "\n" + e.getStackTrace() );
            Log.d("myTag", "err lol ", e);
            e.printStackTrace();
        }
    }

    // Adding new notifications
    void addTaskw(Taskw tsk) {
        try{
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(KEY_WID, tsk.getId()); // Contact Name
            values.put(KEY_WDATE, tsk.getDate());
            values.put(KEY_WEMPID, tsk.getEmpId()); // Contact Phone
            values.put(KEY_WTITLE, tsk.getTitle()); // Contact Name
            values.put(KEY_WSUBJECT, tsk.getSubject()); // Contact Phone
            values.put(KEY_WHOURS, tsk.getHours()); // Contact Name
            values.put(KEY_WMINS, tsk.getMinutes());
            values.put(KEY_WDAYOFWEEK, tsk.getDayOfWeek());
            values.put(KEY_WACCEPTEDAT, tsk.getAcceptedAt());
            values.put(KEY_WSEQID, tsk.getSeqId());

            // Inserting Row

            long rowinsert =  db.insertOrThrow(TABLE_TASKW, null, values);
            Log.d("myTag" ,"rowinsert"+ rowinsert);
            db.close(); // Closing database connection
        }catch(Exception e){ Log.d("myTag", "err" + e.toString() + "\n" +  e.getMessage() + "\n" + e.getCause() + "\n" + e.getStackTrace() );
            Log.d("myTag", "err lol ", e);
            e.printStackTrace();
        }
    }

    // Adding new notifications
    void addTasko(Tasko tsk) {
        try{
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(KEY_OID, tsk.getId()); // Contact Name
            values.put(KEY_OEMPID, tsk.getEmpId()); // Contact Phone
            values.put(KEY_OTITLE, tsk.getTitle()); // Contact Name
            values.put(KEY_OSUBJECT, tsk.getSubject()); // Contact Phone
            values.put(KEY_OHOURS, tsk.getHours()); // Contact Name
            values.put(KEY_OMINS, tsk.getMinutes());
            values.put(KEY_ODATETOSET, tsk.getDateToSet());
            values.put(KEY_OACCEPTEDAT, tsk.getAcceptedAt());
            values.put(KEY_OSEQID, tsk.getSeqId());

            // Inserting Row

            long rowinsert =  db.insertOrThrow(TABLE_TASKO, null, values);
            Log.d("myTag" ,"rowinsert"+ rowinsert);
            db.close(); // Closing database connection
        }catch(Exception e){ Log.d("myTag", "err" + e.toString() + "\n" +  e.getMessage() + "\n" + e.getCause() + "\n" + e.getStackTrace() );
            Log.d("myTag", "err lol ", e);
            e.printStackTrace();
        }
    }

    // Adding new user
    void addSTRoster(StRoster str) {
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            //Log.d("myTag", "adding user db : " + school.getName() + " : " + school.getSchoolId() + " : " + school.getCity());
            ContentValues values = new ContentValues();
            values.put(KEY_STRID, ""); // Contact Name
            values.put(KEY_STRDOFW, str.getDayOfw()); // Contact Phone
            values.put(KEY_STRDATE, str.getDate()); // Contact Name
            values.put(KEY_STRSTATUS, str.getStoreStatus()); // Contact Name
            values.put(KEY_STREVENTS , str.getEvents()); // Contact Phone
            values.put(KEY_STREVENTSTIT , str.getEventTitle()); // Contact Name
            values.put(KEY_STREVENTSUB , str.getEventSub()); // Contact Name
            values.put(KEY_STRSEQID , "");
            values.put(KEY_STRPUSHED , str.getPushed()); // Contact Name
            // Inserting Row
            long rowinsert =  db.insertOrThrow(TABLE_STROSTER, null, values);
            Log.d("myTag" ,"rowinsert"+ rowinsert);
            db.close(); // Closing database connection
        }catch(Exception e){ Log.d("myTag", "err" + e.toString() + "\n" +  e.getMessage() + "\n" + e.getCause() + "\n" + e.getStackTrace() );
            Log.d("myTag", "err lol ", e);
            e.printStackTrace();
        }
    }

    public StRoster getSTRosterByDate(String date){
        SQLiteDatabase db = this.getReadableDatabase();
        StRoster str = null;
        String where = KEY_STRDATE + "= ?";
        Log.d("myTag", "searching for date : " + date);
        String[] whereArgs = {date};
        Cursor cursor = db.query(TABLE_STROSTER,new String[]{KEY_STRDOFW,KEY_STRDATE,KEY_STRSTATUS, KEY_STREVENTS,KEY_STREVENTSTIT, KEY_STREVENTSUB} ,where, whereArgs, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Log.d("myTag", "setting store : " + ""+" : "+ cursor.getString(0)+" : "+ cursor.getString(1) +" : "+cursor.getString(2)+" : "+ cursor.getString(3)+" : "+ cursor.getString(4) +" : "+ cursor.getString(5));
                str = new StRoster("", cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));
            } while (cursor.moveToNext());
        }
        db.close();
        if(str != null) {
            Log.d("myTag", "returning : " + str.getInString());
            return str;
        }
        return null;
    }


    public Boolean containsSTR(String date, String id){
        SQLiteDatabase db = this.getReadableDatabase();
        String where = KEY_STRDATE + "= ? AND " + KEY_STRID + "= ?";
        Log.d("myTag", "searching for date : " + date);
        String[] whereArgs = {date, id};
        Cursor cursor = db.query(TABLE_STROSTER,new String[]{KEY_STRDOFW,KEY_STRDATE,KEY_STRSTATUS, KEY_STREVENTS,KEY_STREVENTSTIT, KEY_STREVENTSUB} ,where, whereArgs, null, null, null, null);
        if(cursor.getCount() == 0)
            return true;
        db.close();
        return false;
    }

    public Boolean containsEMR(String date, String id){
        SQLiteDatabase db = this.getReadableDatabase();
        String where = KEY_EMRDATE + "= ? AND " + KEY_EMRID + "= ?";
        Log.d("myTag", "searching for date : " + date);
        String[] whereArgs = {date, id};
        Cursor cursor = db.query(TABLE_EMPROSTER,new String[]{KEY_EMRDATE} ,where, whereArgs, null, null, null, null);
        if(cursor.getCount() == 0) return true;
        db.close();
        return false;
    }

    public Boolean containsAppli(String date){
        SQLiteDatabase db = this.getReadableDatabase();
        String where = KEY_ADATE + "= ?";
        Log.d("myTag", "searching for date : " + date);
        String[] whereArgs = {date};
        Cursor cursor = db.query(TABLE_APPLICATIONS,new String[]{KEY_ADATE} ,where, whereArgs, null, null, null, null);
        if(cursor.getCount() == 0) return true;
        db.close();
        return false;
    }


    // Adding new user
    void addEmpRoster(EmpRoster str) {
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            //Log.d("myTag", "adding user db : " + school.getName() + " : " + school.getSchoolId() + " : " + school.getCity());
            ContentValues values = new ContentValues();
            values.put(KEY_EMRID, ""); // Contact Name
            values.put(KEY_EMRDOFW, str.getDayOfw()); // Contact Phone
            values.put(KEY_EMREMPID, str.getEmpId()); // Contact Phone
            values.put(KEY_EMRDATE, str.getDate()); // Contact Name
            values.put(KEY_EMRSTATUS, str.getEmpStatus()); // Contact Name
            values.put(KEY_EMRSHIFT , str.getShift()); // Contact Phone
            values.put(KEY_EMRPUSHED , str.getPushed()); // Contact Phone
            // Inserting Row

            long rowinsert =  db.insertOrThrow(TABLE_EMPROSTER, null, values);
            Log.d("myTag" ,"rowinsert"+ rowinsert);
            db.close(); // Closing database connection
        }catch(Exception e){ Log.d("myTag", "err" + e.toString() + "\n" +  e.getMessage() + "\n" + e.getCause() + "\n" + e.getStackTrace() );
            Log.d("myTag", "err lol ", e);
            e.printStackTrace();
        }
    }

    public void viewAllEmpRoster() {
        String selectQuery = "SELECT  * FROM " + TABLE_EMPROSTER ;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Log.d("myTag","getting empr : " + cursor.getString(0) + " : " + cursor.getString(1) + " : " + cursor.getString(2) + " : " + cursor.getString(3) + " : " + cursor.getString(4) + " : " + cursor.getString(5) + " : " + cursor.getString(6) + " : " + cursor.getString(7));
            } while (cursor.moveToNext());
        }
        // return contact list
    }


    public EmpRoster getEmpRosterByDate(String date){
        try{
        SQLiteDatabase db = this.getReadableDatabase();
        EmpRoster str = null;
        String where = KEY_EMRDATE + "= ?";
        String[] whereArgs = {date};
        Cursor cursor = db.query(TABLE_EMPROSTER,new String[]{KEY_EMRID,KEY_EMRDOFW,KEY_EMRDATE, KEY_EMREMPID,KEY_EMRSTATUS, KEY_EMRSHIFT} ,where, whereArgs, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Log.d("myTag", "emr : " + cursor.getString(0) + " : " + cursor.getString(1)+ " : " + cursor.getString(2)+ " : " + cursor.getString(3)+ " : " + cursor.getString(4)+ " : " +cursor.getString(5));
                str = new EmpRoster(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4),cursor.getString(5));
            } while (cursor.moveToNext());
        }
        db.close();
        if(str != null) {
            return str;
        }} catch (SQLException e){Log.d("myTag", "err lol : " , e);}
        return null;
    }

    public EmpRoster getEmpRosterByDate(String date, String empId){
        SQLiteDatabase db = this.getReadableDatabase();
        EmpRoster str = null;
        String where = KEY_STRDATE + "= ? AND " + KEY_EMREMPID + "= ?";
        String[] whereArgs = {date, empId};
        Cursor cursor = db.query(TABLE_EMPROSTER,new String[]{KEY_EMRID,KEY_EMRDOFW,KEY_EMRDATE, KEY_EMREMPID,KEY_EMRSTATUS, KEY_EMRSHIFT, KEY_EMRCHECKIN, KEY_EMRCHECKOUT} ,where, whereArgs, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                str = new EmpRoster(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7));
            } while (cursor.moveToNext());
        }
        db.close();
        if(str != null) {
            return str;
        }
        return null;
    }



    /*
    // Getting notifications Count later for multiple students
    public int getNotifCount(String student) {
        //String countQuery = "SELECT  * FROM  " + TABLE_NOTIFICATION + "WHERE ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NOTIFICATION, new String[] { KEY_ID, KEY_TITLE
                        }, KEY_STUDENT + "=?",
                new String[] { String.valueOf(student) }, null, null, null, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }
*/

    // Getting contacts Count
    public int getNotifCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NOTIFICATION;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int counter = cursor.getCount();

        cursor.close();
        Log.d("myTag", "count : " + cursor.getCount() + " : " + cursor.getColumnCount());
        // return count
        return counter;
    }

    public int getAttendanceCount() {
        String countQuery = "SELECT  * FROM " + TABLE_ATTENDANCE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int counter = cursor.getCount();

        cursor.close();
        Log.d("myTag", "count attenadnce : " + cursor.getCount() + " : " + cursor.getColumnCount());
        // return count
        return counter;
    }

    // Getting contacts Count
    public int getApplicationCount() {
        String countQuery = "SELECT  * FROM " + TABLE_APPLICATIONS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int counter = cursor.getCount();

        cursor.close();
        Log.d("myTag", "count resource : " + cursor.getCount() + " : " + cursor.getColumnCount());
        // return count
        return counter;
    }

    // Getting contacts Count
    public int getEmployeeCount() {
        String countQuery = "SELECT  * FROM " + TABLE_EMPLOYEE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int counter = cursor.getCount();

        cursor.close();
        Log.d("myTag", "count resource : " + cursor.getCount() + " : " + cursor.getColumnCount());
        // return count
        return counter;
    }

    public List<Employee> getEmployeeUnapproved(){
        List<Employee> unappEmp = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Employee emp = null;
        String where = KEY_ETEAMNAME + "= ?";
        String[] whereArgs = {""};
        Cursor cursor = db.query(TABLE_EMPLOYEE,new String[]{KEY_EFIRSTNAME,KEY_EMIDDLENAME,KEY_ELASTNAME,KEY_EPHONE, KEY_EEMAIL,KEY_EAADHARID, KEY_EEID, KEY_EISMANAGER, KEY_ETEAMNAME, KEY_EDESIGNATION, KEY_EMANAGERID} ,where, whereArgs, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Log.d("myTag", "getting employee : " + cursor.getString(0)+" : "+ cursor.getString(1)+" : "+ cursor.getString(2) +" : "+cursor.getString(3)+" : "+ cursor.getString(4)+" : "+ cursor.getString(5) +" : "+ cursor.getString(6));
                emp = new Employee("",cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9), cursor.getString(10));
                unappEmp.add(emp);
            } while (cursor.moveToNext());
        }
        db.close();
        return unappEmp;
    }

    // Getting contacts Count
    public int getTaskdCount() {
        String countQuery = "SELECT  * FROM " + TABLE_TASKD;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int counter = cursor.getCount();

        cursor.close();
        Log.d("myTag", "count : " + cursor.getCount() + " : " + cursor.getColumnCount());
        // return count
        return counter;
    }

    // Getting contacts Count
    public int getTaskwCount() {
        String countQuery = "SELECT  * FROM " + TABLE_TASKW;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int counter = cursor.getCount();

        cursor.close();
        Log.d("myTag", "count : " + cursor.getCount() + " : " + cursor.getColumnCount());
        // return count
        return counter;
    }

    // Getting contacts Count
    public int getTaskoCount() {
        String countQuery = "SELECT  * FROM " + TABLE_TASKO;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int counter = cursor.getCount();

        cursor.close();
        Log.d("myTag", "count : " + cursor.getCount() + " : " + cursor.getColumnCount());
        // return count
        return counter;
    }

    public Taskd getTaskdById(String applicationId){
        SQLiteDatabase db = this.getReadableDatabase();
        Taskd tsk = null;
        String where = KEY_DID + "= ?";
        String[] whereArgs = {applicationId};
        Cursor cursor = db.query(TABLE_TASKD,new String[]{KEY_DID,KEY_DDATE, KEY_DTITLE, KEY_DSUBJECT, KEY_DEMPID, KEY_DHOURS, KEY_DMINS,KEY_DSEQID} ,where, whereArgs, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                tsk = new Taskd(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7));
            } while (cursor.moveToNext());
        }
        db.close();
        if(tsk != null) {
            return tsk;
        }
        return null;
    }

    public ArrayList<TaskInst> getTaskdByDate(String date){
        SQLiteDatabase db = this.getReadableDatabase();
        Taskd tsk = null;
        ArrayList<TaskInst> tList = new ArrayList<>();
        String where = KEY_DDATE + "= ?";
        String[] whereArgs = {date};
        Cursor cursor = db.query(TABLE_TASKD,new String[]{KEY_DID,KEY_DDATE, KEY_DTITLE, KEY_DSUBJECT, KEY_DEMPID, KEY_DHOURS, KEY_DMINS,KEY_DSEQID, KEY_DACCEPTEDAT} ,where, whereArgs, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Log.d("myTag", "setting accepted : " + cursor.getString(8));
                tsk = new Taskd(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8));
                TaskInst tmp = new TaskInst(tsk);
                tList.add(tmp);
            } while (cursor.moveToNext());
        }
        db.close();
        if(tList.size() != 0) {
            return tList;
        }
        return null;
    }


    public Taskw getTaskwById(String Id){
        SQLiteDatabase db = this.getReadableDatabase();
        Taskw tsk = null;
        String where = KEY_WID + "= ?";
        String[] whereArgs = {Id};
        Cursor cursor = db.query(TABLE_TASKW,new String[]{KEY_WID,KEY_WDATE, KEY_WTITLE, KEY_WSUBJECT, KEY_WEMPID, KEY_WHOURS, KEY_WMINS,KEY_WDAYOFWEEK, KEY_WSEQID} ,where, whereArgs, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                tsk = new Taskw(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8));
            } while (cursor.moveToNext());
        }
        db.close();
        if(tsk != null) {
            return tsk;
        }
        return null;
    }

    public ArrayList<TaskInst> getTaskwByDate(String date){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<TaskInst> tList = new ArrayList<>();
        Taskw tsk = null;
        String where = KEY_WDATE + "= ?";
        String[] whereArgs = {date};
        Cursor cursor = db.query(TABLE_TASKW,new String[]{KEY_WID,KEY_WDATE, KEY_WTITLE, KEY_WSUBJECT, KEY_WEMPID, KEY_WHOURS, KEY_WMINS,KEY_WDAYOFWEEK, KEY_WSEQID} ,where, whereArgs, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                tsk = new Taskw(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8));
                TaskInst tmp = new TaskInst(tsk);
                tList.add(tmp);
            } while (cursor.moveToNext());
        }
        db.close();
        if(tList.size() != 0) {
            return tList;
        }
        return null;
    }

    public Tasko getTaskoById(String Id){
        SQLiteDatabase db = this.getReadableDatabase();
        Tasko tsk = null;
        String where = KEY_OID + "= ?";
        String[] whereArgs = {Id};
        Cursor cursor = db.query(TABLE_TASKO,new String[]{KEY_OID, KEY_OTITLE, KEY_OSUBJECT, KEY_OEMPID, KEY_OHOURS, KEY_OMINS,KEY_ODATETOSET,KEY_OACCEPTEDAT, KEY_WSEQID} ,where, whereArgs, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                tsk = new Tasko(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8));
            } while (cursor.moveToNext());
        }
        db.close();
        if(tsk != null) {
            return tsk;
        }
        return null;
    }

    public ArrayList<TaskInst> getTaskoByDate(String date){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<TaskInst> tList = new ArrayList<>();
        Tasko tsk = null;
        String where = KEY_ODATETOSET + "= ?";
        String[] whereArgs = {date};
        Cursor cursor = db.query(TABLE_TASKO,new String[]{KEY_OID, KEY_OTITLE, KEY_OSUBJECT, KEY_OEMPID, KEY_OHOURS, KEY_OMINS,KEY_ODATETOSET,KEY_OACCEPTEDAT, KEY_WSEQID} ,where, whereArgs, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                tsk = new Tasko(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8));
                TaskInst tmp = new TaskInst(tsk);
                tList.add(tmp);
            } while (cursor.moveToNext());
        }
        db.close();
        if(tList.size() != 0) {
            return tList;
        }
        return null;
    }

    public User getUser(){
        User userObj = null;
        String selectQuery = "SELECT  * FROM " + TABLE_USER;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User("",cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7));
                userObj = user;
                Log.d("myTag",cursor.getString(0) + " : " + cursor.getString(1) + " : "  + cursor.getString(2) + " : " + cursor.getString(3) );
            } while (cursor.moveToNext());
        }
        return userObj;
    }

    public ArrayList<Employee> getAllEmployees(){
        ArrayList<Employee> empList = new ArrayList<Employee>();
        String selectQuery = "SELECT  * FROM " + TABLE_EMPLOYEE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Employee emp = new Employee(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8));
                empList.add(emp);
                Log.d("myTag",cursor.getString(0) + " : " + cursor.getString(1) + " : "  + cursor.getString(2) + " : " + cursor.getString(3) + " : " + cursor.getString(4) + " : "  + cursor.getString(5) + " : " + cursor.getString(6) + " : " + cursor.getString(7) + " : " + cursor.getString(8));
            } while (cursor.moveToNext());
        }
        return empList;
    }

    public Employee getEmployeeById(String employeeId){
        SQLiteDatabase db = this.getReadableDatabase();
        Employee emp = null;
        String where = KEY_EEID + "= ?";
        String[] whereArgs = {employeeId};
        Cursor cursor = db.query(TABLE_EMPLOYEE,new String[]{KEY_EFIRSTNAME,KEY_EMIDDLENAME,KEY_ELASTNAME,KEY_EPHONE, KEY_EEMAIL,KEY_EAADHARID, KEY_EEID,KEY_EISMANAGER} ,where, whereArgs, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Log.d("myTag", "getting employee : " + cursor.getString(0)+" : "+ cursor.getString(1)+" : "+ cursor.getString(2) +" : "+cursor.getString(3)+" : "+ cursor.getString(4)+" : "+ cursor.getString(5) +" : "+ cursor.getString(6) +" : "+ cursor.getString(7));
                emp = new Employee(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7));
            } while (cursor.moveToNext());
        }
        db.close();
        if(emp != null) {
            return emp;
        }
        return null;
    }

    public boolean checkEmpExists(String employeeId){
        SQLiteDatabase db = this.getReadableDatabase();
        Employee emp = null;
        String where = KEY_EEID + "= ?";
        String[] whereArgs = {employeeId};
        Cursor cursor = db.query(TABLE_EMPLOYEE,new String[]{KEY_EFIRSTNAME,KEY_EMIDDLENAME,KEY_ELASTNAME,KEY_EPHONE, KEY_EEMAIL,KEY_EAADHARID, KEY_EEID,KEY_EISMANAGER} ,where, whereArgs, null, null, null, null);
        boolean ret = false;
        if(cursor.getCount() != 0){ ret = true;}
        db.close();
        return ret;
    }

    public List<Employee> getAllManagers(){
        SQLiteDatabase db = this.getWritableDatabase();
        Employee emp = null;
        List<Employee> empList = new ArrayList<>();
        String where = KEY_EISMANAGER + "= ?";
        String[] whereArgs = {"true"};
        Cursor cursor = db.query(TABLE_EMPLOYEE,new String[]{KEY_EFIRSTNAME,KEY_EMIDDLENAME,KEY_ELASTNAME,KEY_EPHONE, KEY_EEMAIL,KEY_EAADHARID, KEY_EEID,KEY_EISMANAGER,KEY_ETEAMNAME, KEY_EDESIGNATION} ,where, whereArgs, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Log.d("myTag", "getting employee : " + cursor.getString(0)+" : "+ cursor.getString(1)+" : "+ cursor.getString(2) +" : "+cursor.getString(3)+" : "+ cursor.getString(4)+" : "+ cursor.getString(5) +" : "+ cursor.getString(6) +" : "+ cursor.getString(7) +" : "+ cursor.getString(8));
                emp = new Employee(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7),cursor.getString(8));
            empList.add(emp);
            } while (cursor.moveToNext());
        }
        db.close();
        if(empList.size() != 0) {
            return empList;
        }
        return null;
    }

    public Integer getEmployeesCountTeam(String teamname){
        SQLiteDatabase db = this.getReadableDatabase();
        String where = KEY_ETEAMNAME + "= ?";
        String[] whereArgs = {teamname};
        Cursor cursor = db.query(TABLE_EMPLOYEE,new String[]{KEY_EFIRSTNAME,KEY_EMIDDLENAME,KEY_ELASTNAME,KEY_EPHONE, KEY_EEMAIL,KEY_EAADHARID, KEY_EEID,KEY_EISMANAGER,KEY_ETEAMNAME, KEY_EDESIGNATION} ,where, whereArgs, null, null, null, null);
        Integer count = cursor.getCount();
        db.close();
        return count;
    }


    public ArrayList<Team> getAllTeams(){
        ArrayList<Team> teamList = new ArrayList<Team>();
        String selectQuery = "SELECT  * FROM " + TABLE_TEAM;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Team team = new Team(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
                teamList.add(team);
                Log.d("myTag",cursor.getString(0) + " : " + cursor.getString(1) + " : "  + cursor.getString(2) + " : " + cursor.getString(3) );
            } while (cursor.moveToNext());
        }
        return teamList;
    }

    public Team getTeamByName(String teamName){
        SQLiteDatabase db = this.getReadableDatabase();
        Team team = null;
        String where = KEY_TNAME + "= ?";
        String[] whereArgs = {teamName};
        Cursor cursor = db.query(TABLE_TEAM,new String[]{KEY_TID,KEY_TNAME,KEY_TMANAGERID,KEY_TMANAGERNAME} ,where, whereArgs, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Log.d("myTag", "getting employee : " + cursor.getString(0)+" : "+ cursor.getString(1)+" : "+ cursor.getString(2) +" : "+cursor.getString(3));
                team = new Team(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
            } while (cursor.moveToNext());
        }
        db.close();
        return team;
    }

    public  Store getStore(){
        Store storeObj = null;
        String selectQuery = "SELECT  * FROM " + TABLE_STORE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Store store = new Store(cursor.getString(0), cursor.getString(1),cursor.getString(2), cursor.getString(4), cursor.getString(5),cursor.getString(6),cursor.getString(7), cursor.getString(3), cursor.getString(9),cursor.getString(10),cursor.getString(11), cursor.getString(12), cursor.getString(13),cursor.getString(8));
                storeObj = store;
                Log.d("myTag","getting store : " + cursor.getString(0) + " : " + cursor.getString(1) + " : "  + cursor.getString(2) + " : "+ cursor.getString(3) + " : " + cursor.getString(4) + " : "+ cursor.getString(5) + " : " + cursor.getString(6) + " : " + cursor.getString(7) + " : "  + cursor.getString(8));
            } while (cursor.moveToNext());
        }
        return storeObj;
    }

    // Getting All Contacts
    public ArrayList<Notif> getAllNotifications(int limit , int offset) {
        ArrayList<Notif> notifList = new ArrayList<Notif>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NOTIFICATION + " ORDER BY (" + KEY_NSEQID + ") DESC";
                //+ " LIMIT " + limit;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Notif notification = new Notif();
               // Log.d("myTag","db lol notif : " + cursor.getString(0) + " : " + cursor.getString(1) + " : "  + cursor.getString(2) + " : " + cursor.getString(3) + " : " + cursor.getString(4) + " : " + cursor.getString(5));
                notification.setTitle(cursor.getString(2));
                notification.setSubject(cursor.getString(3));
                notification.setDate(cursor.getString(5));
                // Adding contact to list
                notifList.add(notification);
            } while (cursor.moveToNext());
        }

        // return contact list
        return notifList;
    }

    // Getting All Contacts
    public String getNotifSeq(String type) {
        ArrayList<Notif> notifList = new ArrayList<Notif>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NOTIFICATION + " ORDER BY (" + KEY_NSEQID + ") DESC";
        //+ " LIMIT " + limit;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
String maxSeq = "0";
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                if(cursor.getString(1).equals(type)){
                    if(Integer.parseInt(cursor.getString(6)) > Integer.parseInt(maxSeq))
                        maxSeq = cursor.getString(6);
                }
            } while (cursor.moveToNext());
        }

        // return contact list
        return maxSeq;
    }


    // Getting All Attendance
    public ArrayList<Attendance> getAllAttendance(int limit, int offset) {
        ArrayList<Attendance> attendanceList = new ArrayList<Attendance>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_ATTENDANCE + " LIMIT "+ limit;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Attendance attendance = new Attendance();
                Log.d("myTag",cursor.getString(0) + " : " + cursor.getString(2));
                attendance.setDate(cursor.getString(2));
                attendance.setValue(cursor.getString(0));
                // Adding contact to list
                attendanceList.add(attendance);
            } while (cursor.moveToNext());
        }

        // return contact list
        return attendanceList;
    }

    // Getting All Attendance
    public ArrayList<Application> getAllApplications(int limit, int offset) {
        ArrayList<Application> applicationList = new ArrayList<Application>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_APPLICATIONS ;
                //+ " LIMIT " + limit;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Application application;
                if(cursor.getString(1).equals("")) {
                    application = new Application(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(5), cursor.getString(4));
                    Log.d("myTag", "getting appli : " + cursor.getString(0) + " : " + cursor.getString(1) + " : " + cursor.getString(2) + " : " + cursor.getString(3) + " : " + cursor.getString(4) + " : " + cursor.getString(5));
                }else {
                    application = new Application(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(5), cursor.getString(4),"");
                    Log.d("myTag", "getting appli : " + cursor.getString(0) + " : " + cursor.getString(1) + " : " + cursor.getString(2) + " : " + cursor.getString(3) + " : " + cursor.getString(4) + " : " + cursor.getString(5) + " : " + cursor.getString(6));
                }
                    applicationList.add(application);
            } while (cursor.moveToNext());
        }
        // return contact list
        return applicationList;
    }

    // Getting All Attendance
    public ArrayList<Taskd> getAllTaskd(int limit, int offset) {
        ArrayList<Taskd> taskdList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_TASKD ;
        //+ " LIMIT " + limit;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Taskd tsk = new Taskd(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5), cursor.getString(6), cursor.getString(6));
                Log.d("myTag","getting appli : " + cursor.getString(0) + " : " + cursor.getString(1) + " : " + cursor.getString(2) + " : " + cursor.getString(3) + " : " + cursor.getString(4));
                taskdList.add(tsk);
            } while (cursor.moveToNext());
        }
        // return contact list
        return taskdList;
    }

    public int addManagerTOTeam(String empId, String managerName, String name){
        int ret = 0;
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("managerId", empId);
            cv.put("managerName", managerName);
            String where = KEY_TNAME + "= ?";
            String[] whereArgs = {name};
            ret = db.update(TABLE_TEAM, cv, where, whereArgs);
            db.close();
        }
        catch(SQLException e){
            Log.d("myTag", "error", e);
        }
        return ret;
    }

    public int updateEmp(String empId, String name, String managerId, String designation){
        int ret = 0;
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("teamname", name);
            cv.put("managerId", managerId);
            cv.put("designation", designation);
            String where = KEY_EEID + "= ?";
            String[] whereArgs = {empId};
            ret = db.update(TABLE_EMPLOYEE, cv, where, whereArgs);
            db.close();
        }
        catch(SQLException e){
            Log.d("myTag", "error", e);
        }
        return ret;
    }

    public void updateTeamName(String id, String name){
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("name", name);
            String where = KEY_TID + "= ?";
            String[] whereArgs = {id};
            db.update(TABLE_TEAM, cv, where, whereArgs);
            db.close();
        }
        catch(SQLException e){
            Log.d("myTag", "error", e);
        }
    }



    public void dropAllUserTables() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);
        //noinspection TryFinallyCanBeTryWithResources not available with API < 19
        try {
            List<String> tables = new ArrayList<>(cursor.getCount());

            while (cursor.moveToNext()) {
                tables.add(cursor.getString(0));
            }

            for (String table : tables) {
                if (table.startsWith("sqlite_")) {
                    continue;
                }
                db.execSQL("DROP TABLE IF EXISTS " + table);
                Log.d("myTag", "Dropped table " + table);
            }
        } finally {
            cursor.close();
        }
    }

    public Application getApplicationById(String applicationId){
        SQLiteDatabase db = this.getReadableDatabase();
        Application application = null;
        String where = KEY_AID + "= ?";
        String[] whereArgs = {applicationId};
        Cursor cursor = db.query(TABLE_APPLICATIONS,new String[]{KEY_AID, KEY_ACCEPTANCE, KEY_ATITLE, KEY_ASUBJECT, KEY_ADATE, KEY_AEID} ,where, whereArgs, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                application =  new Application(cursor.getString(0), cursor.getString(1), cursor.getString(2),cursor.getString(3),cursor.getString(4), cursor.getString(5));
            } while (cursor.moveToNext());
        }
        db.close();
        if(application != null) {
            return application;
        }
        return null;
    }

    public void updateApplicationAcceptStatus(String applicationId, String acceptStatus){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("accepted", acceptStatus);
        String where = KEY_AID + "= ?";
        String[] whereArgs = {applicationId};
        db.update(TABLE_APPLICATIONS, cv, where, whereArgs);
        db.close();
    }

    public void deleteRoster(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_EMPROSTER, null, null);
        db.delete(TABLE_STROSTER, null, null);
    }

    public void deleteStore(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_STORE, null, null);
    }

    public void deleteTasks(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TASKD, null, null);
        db.delete(TABLE_TASKW, null, null);
        db.delete(TABLE_TASKO, null, null);
    }

    public void deleteAppli(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE_APPLICATIONS);
    }

    public void deleteEmployees(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE_EMPLOYEE);
    }

    public void deleteTeams(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE_TEAM);
    }
}
