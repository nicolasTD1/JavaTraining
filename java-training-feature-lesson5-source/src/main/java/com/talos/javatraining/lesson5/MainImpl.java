package com.talos.javatraining.lesson5;

import com.talos.javatraining.lesson5.data.OrderData;
import com.talos.javatraining.lesson5.data.OrderEntryData;
import com.talos.javatraining.lesson5.data.PriceData;
//import com.talos.javatraining.lesson5.data.ProductData;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;


/**
 * This implementation uses a traditional for block. Since there are some parts with similar code we created some private methods to reuse that code.
 * However, we need to ref actor this class to use streams instead. In that case the private methods are not longer necessary.
 */
public class MainImpl implements Main
{
	@Override
	public boolean isThereAnOrderWithPriceLowerThan(List<OrderData> orders, BigDecimal price)
	{
		
		return orders.stream().anyMatch( order -> 
										 order.getSubTotal().getValue().compareTo(price) < 0);	
	 }

	@Override
	public boolean areThereAllOrdersWithPriceGreaterThan(List<OrderData> orders, BigDecimal price)
	{
		
		return orders.stream().allMatch( order -> 
										 order.getSubTotal().getValue().compareTo(price) >= 0);
	}

	@Override
	public BigDecimal getLowestOrderPrice(List<OrderData> orders)
	{

		return orders.stream().map(order -> order.getSubTotal())
								.min(Comparator.comparing(PriceData::getValue))
								.map( val -> val.getValue()).get();
	}

	@Override
	public BigDecimal getHighestOrderPrice(List<OrderData> orders)
	{

		return orders.stream().map(order -> order.getSubTotal())
				.max(Comparator.comparing(PriceData::getValue))
				.map( val -> val.getValue()).get();
	}

	@Override
	public long countOrdersWithPriceGreaterThan(List<OrderData> orders, BigDecimal price)
	{
		
		return orders.stream().filter( order -> 
											  order.getSubTotal().getValue().compareTo(price) > 0).count();
	}

	@Override
	public BigDecimal sumOrderPricesWithPriceLowerThan(List<OrderData> orders, BigDecimal price)
	{
		BigDecimal total = BigDecimal.ZERO;
		/*for (OrderData order : orders)
		{
			BigDecimal currentPrice = order.getSubTotal().getValue();
			if (currentPrice.compareTo(price) < 0)
			{
				total = total.add(currentPrice);
			}
		}
		return total;*/
		
		return orders.stream().map(order -> order.getSubTotal().getValue())
							.filter( val -> val.compareTo(price) < 0)
							.reduce(total, (v1,v2) -> v1.add(v2));
	}

	@Override
	public long countAllEntriesWithPriceGreaterThan(List<OrderData> orders, BigDecimal price)
	{
		
		return orders.stream().flatMap(order -> order.getEntries().stream()
							  .map(entry -> entry.getBasePrice().getValue())
							  .filter(valor -> valor.compareTo(price) > 0))
							  .count();
	}

	@Override
	public long countEntriesWithProduct(List<OrderData> orders, String productCode)
	{
		long count = 0;
		/*for (OrderData order : orders)
		{
			for (OrderEntryData entry : order.getEntries())
			{
				ProductData productData = entry.getProduct();
				if (productData.getCode().equals(productCode))
				{
					count++;
				}
			}
		}
		return count;*/
		
		count = orders.stream()
					  .flatMap(order -> order.getEntries().stream()
					  .map(entry -> entry.getProduct())
					  .filter(productData -> productData.getCode().equals(productCode))).count();
			
		return count;
	}

	@Override
	public long sumQuantitiesForProduct(List<OrderData> orders, String productCode)
	{
		long total = 0;
		/*for (OrderData order : orders)
		{
			for (OrderEntryData entry : order.getEntries())
			{
				ProductData productData = entry.getProduct();
				if (productData.getCode().equals(productCode))
				{
					total += getQty(entry);
				}
			}
		}
		return total;*/
		return  orders.stream().flatMap(order -> order.getEntries().stream()
									   .filter(entry -> entry.getProduct().getCode().equals(productCode))
									   .map(val -> getQty(val)))							
									   .reduce(total, Long::sum);
		
		//return total;
	}

	@Override
	public long getMaxQuantityOrderedForProduct(List<OrderData> orders, String productCode)
	{
		//long max = 0;
		/*for (OrderData order : orders)
		{
			for (OrderEntryData entry : order.getEntries())
			{
				ProductData productData = entry.getProduct();
				if (productData.getCode().equals(productCode))
				{
					long quantity = getQty(entry);
					if (quantity > max)
					{
						max = quantity;
					}
				}
			}
		}*/
		
		return orders.parallelStream().flatMap(order -> 
										order.getEntries().stream()
										.filter(entry -> entry.getProduct().getCode().equals(productCode)))
										.max(Comparator.comparing(v->this.getQty(v)))
										.map(quantity -> getQty(quantity))
										.get();
		
		//return max;
	}

	private long getQty(OrderEntryData entry)
	{
		return Optional.ofNullable(entry.getQuantity()).orElse(0L);
	}
}
