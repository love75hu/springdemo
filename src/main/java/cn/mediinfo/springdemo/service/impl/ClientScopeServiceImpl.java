package cn.mediinfo.springdemo.service.impl;

import cn.mediinfo.springdemo.repositoy.ClientScopeRepository;
import cn.mediinfo.springdemo.service.ClientScopeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientScopeServiceImpl implements ClientScopeService {

    @Autowired
    private ClientScopeRepository clientScopeRepository;

    @Override
    public List<String> Get() {
        var list =new ArrayList<String>();
        list.add("陈芳杰");
        list.addAll(clientScopeRepository.Get());
        return list;
    }
}
