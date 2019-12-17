package com.strickers.bankingapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.strickers.bankingapp.dto.FavoritePayeeDto;
import com.strickers.bankingapp.dto.PayeeResponseDto;
import com.strickers.bankingapp.entity.FavoritePayee;
import com.strickers.bankingapp.repository.FavoritePayeeRepository;
import com.strickers.bankingapp.utils.ApiConstant;
import com.strickers.bankingapp.utils.StringConstant;

@Service
public class FavotitePayeeServiceImpl implements FavoritePayeeService {

	@Autowired
	private FavoritePayeeRepository favoritePayeeRepository;

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
			payeeResponseDto.setFavoritePayeeDtos(favoritePayeeDtos);
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
