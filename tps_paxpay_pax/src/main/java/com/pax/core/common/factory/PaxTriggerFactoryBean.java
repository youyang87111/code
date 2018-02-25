package com.pax.core.common.factory;

import org.apache.commons.lang3.StringUtils;
import org.quartz.*;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.Constants;
import org.springframework.util.Assert;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

/**
 * @author :   zhuxl@paxsz.com
 * @time :   2017/6/30
 * @description:
 */

public class PaxTriggerFactoryBean implements FactoryBean<CronTrigger>, BeanNameAware, InitializingBean {
    private static final Logger logger= LoggerFactory.getLogger(PaxTriggerFactoryBean.class);
    private static final Constants constants = new Constants(CronTrigger.class);
    private String run;
    private String name;
    private String group;
    private JobDetail jobDetail;
    private JobDataMap jobDataMap = new JobDataMap();
    private Date startTime;
    private long startDelay = 0L;
    private String cronExpression;
    private TimeZone timeZone;
    private String calendarName;
    private int priority;
    private int misfireInstruction;
    private String description;
    private String beanName;
    private CronTrigger cronTrigger;

    public PaxTriggerFactoryBean() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setJobDetail(JobDetail jobDetail) {
        this.jobDetail = jobDetail;
    }

    public void setJobDataMap(JobDataMap jobDataMap) {
        this.jobDataMap = jobDataMap;
    }

    public JobDataMap getJobDataMap() {
        return this.jobDataMap;
    }

    public void setJobDataAsMap(Map<String, ?> jobDataAsMap) {
        this.jobDataMap.putAll(jobDataAsMap);
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public void setStartDelay(long startDelay) {
        Assert.isTrue(startDelay >= 0L, "Start delay cannot be negative");
        this.startDelay = startDelay;
    }

    public void setCronExpression(String cronExpression) {
        if(StringUtils.isNotBlank(cronExpression)) {
            String[] temp=StringUtils.split(cronExpression,"^");
            this.cronExpression = temp[0];
            try{
                this.run=temp[1].trim();
                if("run".equals(run)){
                    logger.info("定时任务准备启动:任务[{}]设置启动",this.jobDetail.getKey().getGroup()+":"+this.jobDetail.getKey().getName());
                }
            }catch (Exception e){
                logger.info("定时任务准备启动:任务[{}]设置不启动",this.jobDetail.getKey().getGroup()+":"+this.jobDetail.getKey().getName());
            }
        }
    }

    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    public void setCalendarName(String calendarName) {
        this.calendarName = calendarName;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setMisfireInstruction(int misfireInstruction) {
        this.misfireInstruction = misfireInstruction;
    }

    public void setMisfireInstructionName(String constantName) {
        this.misfireInstruction = constants.asNumber(constantName).intValue();
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public void afterPropertiesSet() throws ParseException {
        if(this.name == null) {
            this.name = this.beanName;
        }

        if(this.group == null) {
            this.group = "DEFAULT";
        }

        if(this.jobDetail != null) {
            this.jobDataMap.put("jobDetail", this.jobDetail);
        }

        if(this.startDelay > 0L || this.startTime == null) {
            this.startTime = new Date(System.currentTimeMillis() + this.startDelay);
        }

        if(this.timeZone == null) {
            this.timeZone = TimeZone.getDefault();
        }

        CronTriggerImpl cti = new CronTriggerImpl();
        cti.setName(this.name);
        cti.setGroup(this.group);
        if(this.jobDetail != null) {
            cti.setJobKey(this.jobDetail.getKey());
        }

        cti.setJobDataMap(this.jobDataMap);
        if(StringUtils.equals("run",this.run)) {
            cti.setStartTime(this.startTime);
        }else{
            cti.setStartTime(new Date(System.currentTimeMillis() + 1000*60*60*24*30*12*100));
        }
        cti.setCronExpression(this.cronExpression);
        cti.setTimeZone(this.timeZone);
        cti.setCalendarName(this.calendarName);
        cti.setPriority(this.priority);
        cti.setMisfireInstruction(this.misfireInstruction);
        cti.setDescription(this.description);
        this.cronTrigger = cti;
    }

    public CronTrigger getObject() {
        return this.cronTrigger;
    }

    public Class<?> getObjectType() {
        return CronTrigger.class;
    }

    public boolean isSingleton() {
        return true;
    }

}

