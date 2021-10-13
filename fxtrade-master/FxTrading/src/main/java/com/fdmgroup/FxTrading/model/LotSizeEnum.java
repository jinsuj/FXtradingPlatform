package com.fdmgroup.FxTrading.model;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;



import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonCreator;


@Component
public enum LotSizeEnum {
			STANDARD(100000),
			MINI(10000),
			MICRO(1000),
			NANO(100);
	
	private int lotSize;
	
	private static Map<Integer, LotSizeEnum> FORMAT_MAP = Stream
			.of(LotSizeEnum.values())
			.collect(Collectors.toMap(s -> s.lotSize, Function.identity()));
	
			
	LotSizeEnum(int lotSize){
		this.lotSize = lotSize;
	}
	
	public int getSize() {
		return this.lotSize;
	}
	
	@JsonCreator 
	public static LotSizeEnum fromInteger(Integer integer) {
		return Optional
				.ofNullable(FORMAT_MAP.get(integer))
				.orElseThrow(() -> new IllegalArgumentException(integer.toString()));
	}
	
}
