package com.strickers.bankingapp.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.strickers.bankingapp.dto.FavoritePayeeDto;
import com.strickers.bankingapp.dto.FavoritePayeeRequestDto;
import com.strickers.bankingapp.dto.FavoritePayeeResponseDto;
import com.strickers.bankingapp.dto.PayeeRequestDto;
import com.strickers.bankingapp.dto.PayeeResponseDto;
import com.strickers.bankingapp.dto.PayeesResponseDto;
import com.strickers.bankingapp.entity.Bank;
import com.strickers.bankingapp.entity.Customer;
import com.strickers.bankingapp.entity.FavoritePayee;
import com.strickers.bankingapp.exception.BankNotExistException;
import com.strickers.bankingapp.exception.CustomerNotExistException;
import com.strickers.bankingapp.exception.IfscCodeNotFoundException;
import com.strickers.bankingapp.exception.MaximumFavoriteReachedException;
import com.strickers.bankingapp.exception.PayeeExistException;
import com.strickers.bankingapp.repository.BankRepository;
import com.strickers.bankingapp.repository.CustomerRepository;
import com.strickers.bankingapp.repository.FavoritePayeeRepository;
import com.strickers.bankingapp.utils.ApiConstant;
import com.strickers.bankingapp.utils.StringConstant;

@Service
public class FavoritePayeeServiceImpl implements FavoritePayeeService {

	@Autowired
	FavoritePayeeRepository favoritePayeeRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	BankRepository bankRepository;

	private static final Logger logger = LoggerFactory.getLogger(FavoritePayeeServiceImpl.class);

	/**
	 * @author Hema
	 * @description -> this method is used to add the favorite Payee to the
	 *              particular customerId
	 * @param customerId
	 * @param favoritePayeeRequestDto
	 * @return FavoritePayeeResponseDto
	 */
	@Override
	@Transactional
	public FavoritePayeeResponseDto addFavoritePayee(Integer customerId,
			FavoritePayeeRequestDto favoritePayeeRequestDto) throws MaximumFavoriteReachedException,
			CustomerNotExistException, BankNotExistException, PayeeExistException {
		logger.info("Adding favorite payee");
		Customer customer = customerRepository.findByCustomerId(customerId);
		FavoritePayeeResponseDto favoritePayeeResponseDto = new FavoritePayeeResponseDto();
		if (customer != null) {
			FavoritePayee favoritePayees = favoritePayeeRepository.findByCustomerIdAndAccountNumber(customerId,
					favoritePayeeRequestDto.getAccountNumber());
			if (favoritePayees == null) {
				List<FavoritePayee> favoritePayeeList = favoritePayeeRepository
						.getPayeesByCustomerIdAndStatus(customerId, StringConstant.ACTIVE_STATUS);
				if (favoritePayeeList.size() < StringConstant.MAX_FAVORITE_PAYEES) {
					Bank bank = bankRepository.findByIfscCode(favoritePayeeRequestDto.getIfscCode());
					if (bank != null) {
						FavoritePayee favoritePayee = new FavoritePayee();
						BeanUtils.copyProperties(favoritePayeeRequestDto, favoritePayee);
						favoritePayee.setCustomer(customer);
						favoritePayee.setBank(bank);
						favoritePayee.setStatus(StringConstant.ACTIVE_STATUS);
						favoritePayee.setCreatedDate(LocalDate.now());
						favoritePayee.setUpdatedDate(LocalDate.now());
						FavoritePayee favoritePayee1 = favoritePayeeRepository.save(favoritePayee);
						if (favoritePayee1 != null) {
							favoritePayeeResponseDto.setStatusCode(StringConstant.SUCCESS_STATUS);
							favoritePayeeResponseDto.setMessage(StringConstant.PAYEE_ADDED);
							BeanUtils.copyProperties(favoritePayee, favoritePayeeResponseDto);
						} else {
							favoritePayeeResponseDto.setStatusCode(StringConstant.FAILURE_STATUS);
							favoritePayeeResponseDto.setMessage(StringConstant.PAYEE_NOT_ADDED);
						}
					} else {
						throw new BankNotExistException(StringConstant.BANK_NOT_EXIST);
					}
				} else {
					throw new MaximumFavoriteReachedException(StringConstant.MAX_FAVORITE_REACHED);
				}
			} else {
				throw new PayeeExistException(StringConstant.PAYEE_ALREADY_EXIST);
			}
		} else {
			throw new CustomerNotExistException(StringConstant.CUSTOMER_NOT_EXIST);
		}
		return favoritePayeeResponseDto;
	}

	/* @author Sri Keerthna. @since 2019-12-17
	 * @description Input is given through PayeeResponseDto which is updated in
	 *              database if the payeeId is present and give us a success message
	 * @param payeeRequestDto
	 * @return PayeeResponseDto with favoriteName,status and message
	 * @throws IfscCodeNotFoundException
	 */
	@Override
	public PayeesResponseDto updateFavoritePayee(PayeeRequestDto payeeRequestDto) throws IfscCodeNotFoundException {
		PayeesResponseDto payeeResponseDto = new PayeesResponseDto();
		FavoritePayee favoritePayee = favoritePayeeRepository.findByPayeeId(payeeRequestDto.getPayeeId());
		if (favoritePayee != null) {
			logger.info("Got the payee Id and its details");
			String favoriteName = payeeRequestDto.getFavoriteName();
			Bank bank = bankRepository.findByIfscCode(payeeRequestDto.getIfscCode());
			if (bank != null) {
				logger.info("Got the bank details for that IFSC code");
				BeanUtils.copyProperties(payeeRequestDto, favoritePayee);
				favoritePayee.setBank(bank);
				favoritePayee.setUpdatedDate(LocalDate.now());
				favoritePayeeRepository.save(favoritePayee);
				payeeResponseDto.setFavoriteName(favoriteName);
				payeeResponseDto.setMessage(StringConstant.UPDATED_SUCCESS);
				payeeResponseDto.setStatusCode(StringConstant.SUCCESS_STATUS);
				return payeeResponseDto;
			} else {
				throw new IfscCodeNotFoundException(StringConstant.IFSC_CODE_EXCEPTION);
			}
		} else {
			payeeResponseDto.setMessage(StringConstant.UPDATE_FAILED);
			payeeResponseDto.setStatusCode(StringConstant.FAILURE_STATUS);
			return payeeResponseDto;
		}

	}
	
	/**
	 * @author Sujal
	 * @description This method is used fetch Payees details based on customer
	 * @param searchKey is used to search the above mentioned field of profile
	 * @return PayeeResponseDto is the list of Favorite Payees and response code
	 */
	@Override
	public PayeeResponseDto getPayees(Integer customerId) {
		PayeeResponseDto payeeResponseDto=null;
		List<FavoritePayeeDto> favoritePayeeDtos = new ArrayList<FavoritePayeeDto>();
		List<FavoritePayee> favoritePayees = favoritePayeeRepository.getPayeesByCustomerIdAndStatus(customerId,
				StringConstant.ACTIVE_STATUS);
		if(favoritePayees!=null && !favoritePayees.isEmpty()) {
			payeeResponseDto=new PayeeResponseDto();
			favoritePayees.forEach(favoritePayee -> {
				FavoritePayeeDto favoritePayeeDto= new FavoritePayeeDto();
				BeanUtils.copyProperties(favoritePayee, favoritePayeeDto);
				favoritePayeeDto.setIfscCode(favoritePayee.getBank().getIfscCode());
				favoritePayeeDto.setBankName(favoritePayee.getBank().getBankName());
				favoritePayeeDto.setBranchName(favoritePayee.getBank().getBranchName());
				favoritePayeeDto.setCustomerId(favoritePayee.getCustomer().getCustomerId());

				favoritePayeeDtos.add(favoritePayeeDto);
			});
			payeeResponseDto.setFavoritePayees(favoritePayeeDtos);
			payeeResponseDto.setMessage(ApiConstant.SUCCESS);
			payeeResponseDto.setStatusCode(200);
		}else{
			payeeResponseDto=new PayeeResponseDto();
			payeeResponseDto.setMessage(ApiConstant.FAILED);
			payeeResponseDto.setStatusCode(204);
		}

		return payeeResponseDto;
	}
}
