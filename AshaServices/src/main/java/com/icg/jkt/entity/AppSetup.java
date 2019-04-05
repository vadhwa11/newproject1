package com.icg.jkt.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the APP_SETUP database table.
 * 
 */
@Entity
@Table(name="APP_SETUP")
@NamedQuery(name="AppSetup.findAll", query="SELECT a FROM AppSetup a")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AppSetup implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6202167208046523699L;

	@Id
	@SequenceGenerator(name="APP_SETUP_SEQ", sequenceName="APP_SETUP_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="APP_SETUP_SEQ")
	private long id;

	@Column(name="BEFORE_TIME")
	private String beforeTime;

	@Column(name="BREAK_FROM_TIME")
	private String breakFromTime;

	@Column(name="BREAK_FROM_TIME2")
	private String breakFromTime2;

	@Column(name="BREAK_FROM_TIME3")
	private String breakFromTime3;

	@Column(name="BREAK_TO_TIME")
	private String breakToTime;

	@Column(name="BREAK_TO_TIME2")
	private String breakToTime2;

	@Column(name="BREAK_TO_TIME3")
	private String breakToTime3;

	@Column(name="DAY_OF_WEEK")
	private BigDecimal dayOfWeek;

	@Column(name="\"DAYS\"")
	private String days;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="DEPT_ID")
	private MasDepartment masDepartment;

	@Column(name="DOCTOR_ID")
	private BigDecimal doctorId;

	@Column(name="END_TIME")
	private String endTime;

	@Column(name="FROM_TIME")
	private String fromTime;

	@Temporal(TemporalType.DATE)
	@Column(name="LAST_CHG_DATE")
	private Date lastChgDate;
	

	@Column(name="MAX_NO_OF_DAYS")
	private BigDecimal maxNoOfDays;

	@Column(name="MIN_NO_OF_DAYS")
	private BigDecimal minNoOfDays;

	@Column(name="NO_OF_DOCTOR")
	private BigDecimal noOfDoctor;

	@Column(name="PERCENTAGE_FOR_SLOTS")
	private BigDecimal percentageForSlots;


	@Column(name="SLOT_DURATION")
	private String slotDuration;

	@Column(name="START_TIME")
	private String startTime;

	@Column(name="START_TOKEN")
	private BigDecimal startToken;

	@Column(name="TIME_TAKEN")
	private BigDecimal timeTaken;

	@Column(name="TO_TIME")
	private String toTime;

	@Column(name="TOTAL_INTERVAL")
	private BigDecimal totalInterval;

	@Column(name="TOTAL_ONLINE_TOKEN")
	private BigDecimal totalOnlineToken;

	@Column(name="TOTAL_TOKEN")
	private BigDecimal totalToken;

	@Column(name="VALID_FROM")
	private Timestamp validFrom;

	@Column(name="VALID_TO")
	private Timestamp validTo;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="HOSPITAL_ID")
	private MasHospital masHospital;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SESSION_ID")
	private MasAppointmentSession masAppointmentSession;
	

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="LAST_CHG_BY")
	private Users user;

	public AppSetup() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBeforeTime() {
		return this.beforeTime;
	}

	public void setBeforeTime(String beforeTime) {
		this.beforeTime = beforeTime;
	}

	public String getBreakFromTime() {
		return this.breakFromTime;
	}

	public void setBreakFromTime(String breakFromTime) {
		this.breakFromTime = breakFromTime;
	}

	public String getBreakFromTime2() {
		return this.breakFromTime2;
	}

	public void setBreakFromTime2(String breakFromTime2) {
		this.breakFromTime2 = breakFromTime2;
	}

	public String getBreakFromTime3() {
		return this.breakFromTime3;
	}

	public void setBreakFromTime3(String breakFromTime3) {
		this.breakFromTime3 = breakFromTime3;
	}

	public String getBreakToTime() {
		return this.breakToTime;
	}

	public void setBreakToTime(String breakToTime) {
		this.breakToTime = breakToTime;
	}

	public String getBreakToTime2() {
		return this.breakToTime2;
	}

	public void setBreakToTime2(String breakToTime2) {
		this.breakToTime2 = breakToTime2;
	}

	public String getBreakToTime3() {
		return this.breakToTime3;
	}

	public void setBreakToTime3(String breakToTime3) {
		this.breakToTime3 = breakToTime3;
	}

	public BigDecimal getDayOfWeek() {
		return this.dayOfWeek;
	}

	public void setDayOfWeek(BigDecimal dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public String getDays() {
		return this.days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	

	public MasDepartment getMasDepartment() {
		return masDepartment;
	}

	public void setMasDepartment(MasDepartment masDepartment) {
		this.masDepartment = masDepartment;
	}

	public BigDecimal getDoctorId() {
		return this.doctorId;
	}

	public void setDoctorId(BigDecimal doctorId) {
		this.doctorId = doctorId;
	}

	public String getEndTime() {
		return this.endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getFromTime() {
		return this.fromTime;
	}

	public void setFromTime(String fromTime) {
		this.fromTime = fromTime;
	}

	public Date getLastChgDate() {
		return this.lastChgDate;
	}

	public void setLastChgDate(Date lastChgDate) {
		this.lastChgDate = lastChgDate;
	}


	public BigDecimal getMaxNoOfDays() {
		return this.maxNoOfDays;
	}

	public void setMaxNoOfDays(BigDecimal maxNoOfDays) {
		this.maxNoOfDays = maxNoOfDays;
	}

	public BigDecimal getMinNoOfDays() {
		return this.minNoOfDays;
	}

	public void setMinNoOfDays(BigDecimal minNoOfDays) {
		this.minNoOfDays = minNoOfDays;
	}

	public BigDecimal getNoOfDoctor() {
		return this.noOfDoctor;
	}

	public void setNoOfDoctor(BigDecimal noOfDoctor) {
		this.noOfDoctor = noOfDoctor;
	}

	public BigDecimal getPercentageForSlots() {
		return this.percentageForSlots;
	}

	public void setPercentageForSlots(BigDecimal percentageForSlots) {
		this.percentageForSlots = percentageForSlots;
	}

	

	public String getSlotDuration() {
		return this.slotDuration;
	}

	public void setSlotDuration(String slotDuration) {
		this.slotDuration = slotDuration;
	}

	public String getStartTime() {
		return this.startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public BigDecimal getStartToken() {
		return this.startToken;
	}

	public void setStartToken(BigDecimal startToken) {
		this.startToken = startToken;
	}

	public BigDecimal getTimeTaken() {
		return this.timeTaken;
	}

	public void setTimeTaken(BigDecimal timeTaken) {
		this.timeTaken = timeTaken;
	}

	public String getToTime() {
		return this.toTime;
	}

	public void setToTime(String toTime) {
		this.toTime = toTime;
	}

	public BigDecimal getTotalInterval() {
		return this.totalInterval;
	}

	public void setTotalInterval(BigDecimal totalInterval) {
		this.totalInterval = totalInterval;
	}

	public BigDecimal getTotalOnlineToken() {
		return this.totalOnlineToken;
	}

	public void setTotalOnlineToken(BigDecimal totalOnlineToken) {
		this.totalOnlineToken = totalOnlineToken;
	}

	public BigDecimal getTotalToken() {
		return this.totalToken;
	}

	public void setTotalToken(BigDecimal totalToken) {
		this.totalToken = totalToken;
	}

	public Timestamp getValidFrom() {
		return this.validFrom;
	}

	public void setValidFrom(Timestamp validFrom) {
		this.validFrom = validFrom;
	}

	public Timestamp getValidTo() {
		return this.validTo;
	}

	public void setValidTo(Timestamp validTo) {
		this.validTo = validTo;
	}

	public MasHospital getMasHospital() {
		return this.masHospital;
	}

	public void setMasHospital(MasHospital masHospital) {
		this.masHospital = masHospital;
	}

	public Users getUser() {
		return this.user;
	}

	public void setUser(Users user) {
		this.user = user;
	}
	
	public MasAppointmentSession getMasAppointmentSession() {
		return masAppointmentSession;
	}

	public void setMasAppointmentSession(MasAppointmentSession masAppointmentSession) {
		this.masAppointmentSession = masAppointmentSession;
	}

}