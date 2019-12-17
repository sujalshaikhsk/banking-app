package com.strickers.bankingapp.service;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.strickers.bankingapp.dto.FavoritePayeeRequestDto;
import com.strickers.bankingapp.dto.FavoritePayeeResponseDto;
import com.strickers.bankingapp.entity.Bank;
import com.strickers.bankingapp.entity.Customer;
import com.strickers.bankingapp.entity.FavoritePayee;
import com.strickers.bankingapp.exception.BankNotExistException;
import com.strickers.bankingapp.exception.CustomerNotExistException;
import com.strickers.bankingapp.exception.MaximumFavoriteReachedException;
import com.strickers.bankingapp.exception.PayeeExistException;
import com.strickers.bankingapp.repository.BankRepository;
import com.strickers.bankingapp.repository.CustomerRepository;
import com.strickers.bankingapp.repository.FavoritePayeeRepository;
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

}
