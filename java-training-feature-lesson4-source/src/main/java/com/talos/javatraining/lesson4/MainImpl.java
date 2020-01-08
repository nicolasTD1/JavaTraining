package com.talos.javatraining.lesson4;


import com.talos.javatraining.lesson4.exceptions.AddressNotFoundException;
import com.talos.javatraining.lesson4.model.AddressModel;
import com.talos.javatraining.lesson4.model.CountryModel;
import com.talos.javatraining.lesson4.model.UserModel;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;


public class MainImpl implements Main
{

	@Override
	public String getLine1(AddressModel addressModel)
	{
 		
		return Optional.ofNullable(addressModel)
					   .map(AddressModel::getLine1)
					   .filter(StringUtils::isNotBlank)
					   .orElse("");
		
	}

	@Override
	public String getFullName(AddressModel addressModel)
	{
		StringBuilder stringBuilder = new StringBuilder();
		/*if (addressModel != null)
		{
			if (StringUtils.isNotBlank(addressModel.getFirstName()))
			{
				stringBuilder.append(addressModel.getFirstName());
			}
			if (StringUtils.isNotBlank(addressModel.getLastName()))
			{
				if (stringBuilder.length() != 0)
				{
					stringBuilder.append(StringUtils.SPACE);
				}
				stringBuilder.append(addressModel.getLastName());
			}
		}
		return stringBuilder.toString();*/
		
		stringBuilder.append(Optional.ofNullable(addressModel)
				.map(AddressModel::getFirstName)
				.filter(StringUtils::isNotBlank)
				.orElse(""));
		Optional.ofNullable(addressModel)
				.map(AddressModel::getLastName)
				.filter(StringUtils::isNotBlank)
				.ifPresent((espacio)-> {
					if (stringBuilder.length() != 0){
						stringBuilder.append(StringUtils.SPACE);
					}
					stringBuilder.append(espacio); });
				
		return stringBuilder.toString();
	}

	@Override
	public AddressModel getBillingAddress(UserModel userModel)
	{
		
		//AddressModel result = null;
		/*if (userModel != null)
		{
			if (CollectionUtils.isNotEmpty(userModel.getAddresses()))
			{
				result = getAddress(userModel.getAddresses(), a -> BooleanUtils.isTrue(a.getBillingAddress()));
			}
		}
		return result;*/
		
		return Optional.ofNullable(userModel)
					   .map(UserModel::getAddresses)
					   .filter(CollectionUtils::isNotEmpty)
					   .map(addresses->getAddress(addresses,a -> BooleanUtils.isTrue(a.getBillingAddress())))
					   .orElse(null);
	}

	@Override
	public String getLastLoginFormatted(UserModel userModel)
	{
		DateFormat formatt = new SimpleDateFormat("MM/dd/yyyy");
		String result = "the user has not been logged yet";
		
		/*if (userModel != null && userModel.getLastLogin() != null)
		{
			result = formatt.format(userModel.getLastLogin());
		}*/

		return Optional.ofNullable(userModel)
						 .map(UserModel::getLastLogin)
						 .map(resultt -> formatt.format(resultt))
						 .orElse(result)
						 .toString();
	}

	@Override
	public String getContactCountry(UserModel userModel)
	{
		String contactAddressIsoCode = null;
		/*if (userModel != null)
		{
			if (CollectionUtils.isNotEmpty(userModel.getAddresses()))
			{
				AddressModel contactAddress = getAddress(userModel.getAddresses(), a -> BooleanUtils.isTrue(a.getContactAddress()));
				if (contactAddress != null && contactAddress.getCountry() != null)
				{
					contactAddressIsoCode = contactAddress.getCountry().getIsocode();
				}
			}
		}
		if (contactAddressIsoCode == null)
		{
			contactAddressIsoCode = inferCountry();
		}*/
		
		AddressModel contactAddress  = Optional.ofNullable(userModel)
				.map(UserModel::getAddresses)
				.filter(CollectionUtils::isNotEmpty)
				.map(addres -> getAddress(addres, a -> BooleanUtils.isTrue(a.getContactAddress())))
				.orElse(null);
		
		contactAddressIsoCode = Optional.ofNullable(contactAddress)
										  .map(AddressModel::getCountry)
										  .map(CountryModel::getIsocode)
										  .orElseGet(this::inferCountry);	
				
		return contactAddressIsoCode;
		
		
	}

	@Override
	public AddressModel getShippingAddress(UserModel userModel) throws AddressNotFoundException
	{
		AddressModel addressModel = null;
		/*if (CollectionUtils.isNotEmpty(userModel.getAddresses()))
		{
			addressModel = getAddress(userModel.getAddresses(), a -> BooleanUtils.isTrue(a.getShippingAddress()));
		}
		if(addressModel == null){
			throw new AddressNotFoundException();
		}*/
		
		addressModel = Optional.ofNullable(userModel)
							  .map(UserModel::getAddresses)
							  .filter(CollectionUtils::isNotEmpty)
							  .map(b ->getAddress(b, a -> BooleanUtils.isTrue(a.getShippingAddress())))
							  .orElseThrow(AddressNotFoundException::new)
							  ;
		
		
		return addressModel;
	}

	// ----------------------------------
	// DON'T MODIFY THE FOLLOWING METHODS
	// ----------------------------------

	/**
	 * This method returns an address based on the condition
	 *
	 * @param addresses the address list
	 * @param condition the condition
	 * @return the first address that matches the condition
	 */
	private AddressModel getAddress(Collection<AddressModel> addresses, Predicate<AddressModel> condition)
	{
		for (AddressModel addressModel : addresses)
		{
			if (condition.test(addressModel))
			{
				return addressModel;
			}
		}
		return null;
	}

	/**
	 * This method takes 1 second to return a response
	 *
	 * @return the user country
	 */
	private String inferCountry()
	{
		try
		{
			Thread.sleep(1000);
		}
		catch (InterruptedException ex)
		{

		}
		return "CA";
	}
}
