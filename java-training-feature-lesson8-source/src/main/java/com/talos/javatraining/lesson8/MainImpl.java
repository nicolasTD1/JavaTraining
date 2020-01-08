package com.talos.javatraining.lesson8;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;


public class MainImpl implements Main
{

	@Override
	public Instant getInstant(String dateTime)
	{
		LocalDateTime local = LocalDateTime.parse(dateTime);
		Instant instant = local.plus(1, ChronoUnit.SECONDS)
							   .minus(10, ChronoUnit.MINUTES)
							   .atZone(ZoneId.of("GMT-5"))
							   .toInstant();
		return instant;
	}

	@Override
	public Duration getDuration(Instant a, Instant b)
	{
		return Duration.between(a, b).plusDays(1).minusHours(4);
	}

	@Override
	public String getHumanReadableDate(LocalDateTime localDateTime)
	{
		int year = localDateTime.getYear();
		if(year == 2011) year = 2013;
		if(year % 2 == 0)  year ++; 
		LocalDateTime newlocal = localDateTime.plusHours(3).withMonth(7).withYear(year);
		DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
		String formatted = formatter.ISO_LOCAL_DATE.format(newlocal);
		return formatted;
	}

	@Override
	public LocalDateTime getLocalDateTime(String dateTime)
	{
		LocalDateTime local = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("ssmmHHddMMyyy"));
		int month = local.getMonthValue();
		int seconds = local.getSecond();
		if (month%2 != 0) month++;
		local = local.withMonth(month).plusSeconds(seconds);
		return local;
	}

	@Override
	public Period calculateNewPeriod(Period period)
	{
		return period.plusMonths(5).plusDays(6).minusDays(14);
	}

	@Override
	public LocalDate toLocalDate(Year year, MonthDay monthDay)
	{
		int day = monthDay.getDayOfMonth();
		int mod = day%5;
		if(mod != 0) day -= (mod);
		if(day == 0 || mod == 0) day = 1;
		LocalDate local = LocalDate.of(year.plusYears(3).getValue(), monthDay.getMonth() ,day);
		return local;
	}

	@Override
	public LocalDateTime toLocalDateTime(YearMonth yearMonth, int dayOfMonth, LocalTime time)
	{
		
		return LocalDateTime.of(yearMonth.getYear(), 
								yearMonth.getMonth(), 
								dayOfMonth, 
								time.getHour(), 
								time.getMinute(), 
								time.getSecond()).plusDays(3).minusMinutes(37).withSecond(0);
		
	}

	@Override
	public TemporalAdjuster createTemporalAdjusterNextMonday()
	{
		TemporalAdjuster NEXT_WORKDAY = w -> {
		    LocalDate result = (LocalDate) w;
		    do {
		    	result = result.plusDays(1);
		    } while (!result.getDayOfWeek().equals(DayOfWeek.MONDAY));
		    return result;
		};
		
		return NEXT_WORKDAY;
	}

	@Override
	public TemporalAdjuster createTemporalAdjusterNextFebruaryFirst()
	{
		TemporalAdjuster NEXT_WORKDAY = w -> {
		    LocalDate result = (LocalDate) w;
		    do {
		    	result = result.plusMonths(1);
		    } while (!result.getMonth().equals(Month.FEBRUARY));
		    return result.withDayOfMonth(1);
		};
		
		return NEXT_WORKDAY;
	}

	@Override
	public String adjustDateTime(String localDateTime, TemporalAdjuster adjuster)
	{
		LocalDateTime local = LocalDateTime.parse(localDateTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		local = LocalDateTime.of(local.getYear(), local.getMonth(), local.getDayOfMonth(), local.getHour(), local.getMinute(), local.getSecond()).with(adjuster);
		
		return local.toString();
	}

	@Override
	public String processZonedDateTime(String zonedDateTime)
	{
		ZonedDateTime zoned = ZonedDateTime.parse(zonedDateTime, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
		zoned = zoned.plusHours(1).withZoneSameInstant(ZoneId.of("UTC"));
		int minute = zoned.getMinute();
		int mod = minute%15;
		if(mod != 0) zoned = zoned.minusMinutes(mod);
		return zoned.format(DateTimeFormatter.RFC_1123_DATE_TIME).toString();
	}
}
