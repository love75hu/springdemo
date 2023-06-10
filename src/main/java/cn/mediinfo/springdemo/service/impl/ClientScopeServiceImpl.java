package cn.mediinfo.springdemo.service.impl;

import cn.mediinfo.springdemo.model.ClientscopeEntity;
import cn.mediinfo.springdemo.repositoy.ClientScopeRepository;
import cn.mediinfo.springdemo.service.ClientScopeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientScopeServiceImpl implements ClientScopeService {

    private final ClientScopeRepository clientScopeRepository;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<String> Get() {
        var list =new ArrayList<String>();
        list.add("陈芳杰");
        list.addAll(clientScopeRepository.Get());

        return list.stream().filter(x->!x.startsWith("陈")).toList();
        //return list;
    }

    @Override
    public List<ClientscopeEntity> GetById(String Id) {
        Pageable pageable = PageRequest.of(0, 10);

        var customer = new ClientscopeEntity();
        customer.setId(Id);
        Example<ClientscopeEntity> example = Example.of(customer);
        return clientScopeRepository.findAll(example,pageable).stream().toList();
    }

    @Override
    public List<ClientscopeEntity> GetPageable(int Page, int Size) {
        Pageable pageable = PageRequest.of(Page, Size);
        return clientScopeRepository.findAll(pageable).stream().toList();
    }
}
