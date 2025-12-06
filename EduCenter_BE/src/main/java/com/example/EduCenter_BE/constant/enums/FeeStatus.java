package com.example.EduCenter_BE.constant.enums;

public enum FeeStatus {

    UNPAID, 	    //Học phí chưa đóng
    PARTIAL_PAID,	//Đã đóng một phần (có trả góp hoặc đóng nhiều lần)
    PAID,	        //Đã đóng đủ
    OVERDUE,	    //Quá hạn nhưng chưa đóng
    CANCELLED	    //Hủy hóa đơn / không còn hiệu lực

}
