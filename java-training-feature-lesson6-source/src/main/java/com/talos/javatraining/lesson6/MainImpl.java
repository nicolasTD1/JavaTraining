package com.talos.javatraining.lesson6;

import com.talos.javatraining.lesson6.data.OrderData;
import com.talos.javatraining.lesson6.data.OrderEntryData;
import com.talos.javatraining.lesson6.data.ProductData;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import static java.util.Comparator.comparing;
import static org.apache.commons.collections4.ComparatorUtils.chainedComparator;


public class MainImpl implements Main
{
	private static final Comparator<Map.Entry<ProductData, Long>> COMPARATOR = chainedComparator(comparing(Map.Entry::getValue), comparing(e -> e.getKey().getCode()));
	private static final String PIPELINE = "|";

	@Override
	public List<OrderData> getOrdersWithPriceGreaterThan(List<OrderData> orders, BigDecimal price, long limit)
	{
		//List<OrderData> result = new ArrayList<>();
		//int i = 0;
		/*for (OrderData order : orders)
		{
			if (order.getSubTotal().getValue().compareTo(price) > 0)
			{
				if (i++ < limit)
				{
					result.add(order);
				}
				else
				{
					break;
				}
			}
		}
		return result;*/
		
		return orders.stream()
				       .filter( order -> order.getSubTotal().getValue().compareTo(price) > 0 )
				       .limit(limit)
				       .collect(Collectors.toList());
		
		//return result;
	}

	@Override
	public List<OrderData> getOrdersThatContainsAProduct(List<OrderData> orders, String productCode)
	{
		//List<OrderData> result = new ArrayList<>();
		/*for (OrderData order : orders)
		{
			boolean contains = false;
			for (OrderEntryData entry : order.getEntries())
			{
				if (productCode.equals(entry.getProduct().getCode()))
				{
					contains = true;
					break;
				}
			}
			if (contains)
			{
				result.add(order);
			}
		}
		
		return result;*/
		
		return orders.stream()
					 .filter(order -> order.getEntries().stream()
					 .anyMatch( entry ->productCode.equals(entry.getProduct().getCode())))
					 .collect(Collectors.toList());
	}

	@Override
	public Set<String> getOrderCodes(List<OrderData> orders)
	{
		// In order to pass the test, you have to use a TreeSet
		
		Set<String> result = new TreeSet<>();
		/*for (OrderData order : orders)
		{
			result.add(order.getCode());
		}*/
		
		result = orders.stream()
				.map(order -> order.getCode())
				.collect(Collectors.toCollection(TreeSet::new));
		
		return result;
	}

	@Override
	public List<OrderEntryData> getEntriesWithPriceLowerThan(List<OrderData> orders, BigDecimal price)
	{
		List<OrderEntryData> result = new ArrayList<>();
		/*for (OrderData order : orders)
		{
			for (OrderEntryData entry : order.getEntries())
			{
				if (entry.getBasePrice().getValue().compareTo(price) < 0)
				{
					result.add(entry);
				}
			}
		}
		return result;*/
		
		result = orders.stream()
			       .flatMap( order -> order.getEntries().stream())
			       .filter(entry -> entry.getBasePrice().getValue().compareTo(price) < 0)
			       .collect(Collectors.toList());
		
		return result;
	}

	@Override
	public Map<Integer, OrderEntryData> getEntriesAsMap(List<OrderData> orders, String orderCode)
	{
		OrderData selected = null;
		/*for (OrderData order : orders)
		{
			if (order.getCode().equals(orderCode))
			{
				selected = order;
				break;
			}
		}
		*/
		
		selected = orders.stream()
						 .filter(order -> order.getCode().equals(orderCode))
						 .findFirst().orElse(null);

		if (selected == null)
		{
			return Collections.EMPTY_MAP;
		}
		
		Map<Integer, OrderEntryData> entries = new HashMap<>();
		/*for (OrderEntryData entry : selected.getEntries())
		{
			entries.put(entry.getEntryNumber(), entry);
		}*/
		
		entries = selected.getEntries().stream()
				                       .map(p -> p)
				                       .collect(Collectors.toMap(entry -> entry.getEntryNumber(), entry -> entry));	
			
		return entries;
	}

	@Override
	public String getEntriesAsString(List<OrderData> orders, String orderCode)
	{
		OrderData selected = null;
		/*for (OrderData order : orders)
		{
			if (order.getCode().equals(orderCode))
			{
				selected = order;
				break;
			}
		}*/
		
		selected = orders.stream()
				 .filter(order -> order.getCode().equals(orderCode))
				 .findFirst().orElse(null);
		
		if (selected == null)
		{
			return StringUtils.EMPTY;
		}
		
		//StringBuilder builder = new StringBuilder();
		/*for (OrderEntryData entry : selected.getEntries())
		{
			builder.append(PIPELINE).append(entry.toString());
		}*/
		
		//builder = selected.getEntries().stream()
		//							   .map(i -> i.toString())
		//							   .collect(Collectors.joining(PIPELINE));
						  //.collect(StringBuilder::new, (x, y) -> x.append(y),  (a, b) -> a.append(this.PIPELINE).append(b));
		
		String result = "";
		
		result = selected.getEntries().stream()
				   .map(i -> i.toString())
				   .collect(Collectors.joining(PIPELINE));
		
		return result;
	}

	@Override
	public Map<String, List<ProductData>> getProductsByOrderCode(List<OrderData> orders)
	{
		Map<String, List<ProductData>> result = new HashMap<>();
		/*for (OrderData order : orders)
		{
			for (OrderEntryData entry : order.getEntries())
			{
				result.compute(order.getCode(), (k, v) -> {
					if (v == null)
					{
						v = new ArrayList<>();
					}
					v.add(entry.getProduct());
					return v;
				});
			}
		}*/
		
		result = orders.stream().collect(Collectors.toMap(OrderData::getCode, x-> x.getEntries()
												   .stream()
												   .map(OrderEntryData::getProduct)
												   .collect(Collectors.toList())));
		
		return result;
	}
	
	@Override
	public List<ProductData> getBestSellingProducts(List<OrderData> orders, long limit)
	{
		Map<ProductData, Long> counter = new HashMap<>();
		/*for (OrderData order : orders)
		{
			for (OrderEntryData entry : order.getEntries())
			{
				counter.compute(entry.getProduct(), (k, v) -> {
					if (v == null)
					{
						v = 0L;
					}
					return v + entry.getQuantity();
				});
			}
		}*/
		
		counter = orders.stream()
						.flatMap(order -> order.getEntries().stream())
						.collect(Collectors.toMap(OrderEntryData::getProduct, entry -> entry.getQuantity() , (o1, o2) -> o1 + o2));
	
		
		Set<Map.Entry<ProductData, Long>> finalList = new TreeSet<>(COMPARATOR.reversed());
		finalList.addAll(counter.entrySet());
		List<ProductData> result = new ArrayList<>();
		/*int i = 0;
		for (Map.Entry<ProductData, Long> entry : finalList)
		{
			if (i++ < limit)
			{
				result.add(entry.getKey());
			}
			else
			{
				break;
			}
		}*/
		result = finalList.stream()
						  .limit(limit)
						  .map(Map.Entry::getKey)
						  .collect(Collectors.toList());
		return result;
	}
}
