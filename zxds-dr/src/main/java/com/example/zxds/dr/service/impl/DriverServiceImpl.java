package com.example.zxds.dr.service.impl;

import com.example.zxds.dr.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;


@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {
    @Override
    @Transactional
    public long registerNewDriver(Map<String, Object> params) {

        return 0;
    }
}
