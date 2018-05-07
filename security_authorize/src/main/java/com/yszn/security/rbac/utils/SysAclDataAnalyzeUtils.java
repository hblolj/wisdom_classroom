package com.yszn.security.rbac.utils;

import com.yszn.security.rbac.model.SysAclData;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: hblolj
 * @Date: 2018/4/20 15:37
 * @Description:
 * @Version: 1.0
 **/
public class SysAclDataAnalyzeUtils {

    public static Boolean validateAclCanVisitByLimit(List<SysAclData> limits, Map<String, String[]> requestMap){
        // 按 seq 进行排序，防止 nextParamOp 错乱
        Set<SysAclData> collect = limits.stream().sorted(Comparator.comparingInt(SysAclData::getSeq)).collect(Collectors.toSet());
        Boolean result = true;
        Integer nextParamOp = 1;
        // true && true
        // true && false
        for (SysAclData limit : collect){
            boolean tempResult;
            String param = limit.getParam();
            Integer operation = limit.getOperation();
            String value1 = limit.getValue1();
            String value2 = limit.getValue2();
            Integer tempNextParamOp = limit.getNextParamOp();
            Byte seq = limit.getSeq();
            // 获取限制的参数值
            String[] values = requestMap.get(param);
            // 操作类型，0；等于，1：大于，2：小于，3：大于等于，4：小于等于，5：传入的参数包含限制值，6：传入的参数介于限制值之间，wait custom...
            switch (operation){
                case 0:
                    tempResult = value1.equals(values[0]);
                    break;
                case 1:
                    tempResult = Integer.parseInt(values[0]) > Integer.parseInt(value1);
                    break;
                case 2:
                    tempResult = Integer.parseInt(values[0]) < Integer.parseInt(value1);
                    break;
                case 3:
                    tempResult = Integer.parseInt(values[0]) >= Integer.parseInt(value1);
                    break;
                case 4:
                    tempResult = Integer.parseInt(values[0]) <= Integer.parseInt(value1);
                    break;
                case 5:
                    tempResult = Arrays.asList(values).contains(value1);
                    break;
                case 6:
                    tempResult = Integer.parseInt(values[0]) >= Integer.parseInt(value1) && Integer.parseInt(values[0]) <= Integer.parseInt(value2);
                    break;
                default:
                    tempResult = false;
                    break;
            }
            if (nextParamOp == 1){
                result = result && tempResult;
            }else if (nextParamOp == 2){
                result = result || tempResult;
            }
            nextParamOp = tempNextParamOp;
        }
        return result;
    }
}
