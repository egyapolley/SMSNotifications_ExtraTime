package com.soap;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = {"callingSubscriber","phoneContact","smsId","details"})
public class SoapRequest {

    private String callingSubscriber;
    private String phoneContact;
    private int smsId;
    private String details;



    public SoapRequest() {
    }

    public SoapRequest(String callingSubscriber, String phoneContact, int smsId) {
        this.callingSubscriber = callingSubscriber;
        this.phoneContact = phoneContact;
        this.smsId = smsId;
    }

    public String getCallingSubscriber() {
        return callingSubscriber;
    }

    public void setCallingSubscriber(String callingSubscriber) {
        this.callingSubscriber = callingSubscriber;
    }

    public String getPhoneContact() {
        return phoneContact;
    }

    public void setPhoneContact(String phoneContact) {
        this.phoneContact = phoneContact;
    }

    public int getSmsId() {
        return smsId;
    }

    public void setSmsId(int smsId) {
        this.smsId = smsId;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
