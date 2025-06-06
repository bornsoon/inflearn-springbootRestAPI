package com.helloworld.quickstart.service;

import com.helloworld.quickstart.dto.ItemDto;
import com.helloworld.quickstart.entity.ItemEntity;
import com.helloworld.quickstart.mapper.QuickMapper;
import com.helloworld.quickstart.repository.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@Slf4j
public class QuickService {

    @Autowired
    private QuickMapper quickMapper;

    @Autowired
    private ItemRepository itemRepository;

    // JPA -> setName 메소드 바로 사용 가능
    public boolean registerItem(ItemDto itemDto) {
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setId(itemDto.getId());
        itemEntity.setName(itemDto.getName());

        itemRepository.save(itemEntity);

        return true;
    }

    // JPA -> findById 메소드 바로 사용 가능
    public ItemDto getItemById(String id) {
        ItemEntity itemEntity = itemRepository.findById(id).get();

        ItemDto itemDto = new ItemDto();

        itemDto.setId(itemEntity.getId());
        itemDto.setName(itemEntity.getName());

        return itemDto;
    }

    /* myBatis

    public boolean registerItem(ItemDto itemDto) {
        // TODO: DB insert
        HashMap<String, Object> paramMap = new HashMap<>();

        paramMap.put("id", itemDto.getId());
        paramMap.put("name", itemDto.getName());

        quickMapper.registerItem(paramMap);

        log.info("service ...");

        return true;
    }

    public ItemDto getItemById(String id) {

        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);

        HashMap<String, Object> res = quickMapper.findById(paramMap);

        ItemDto itemDto = new ItemDto();
        itemDto.setId((String)res.get("ID"));
        itemDto.setName((String)res.get("NAME"));

        return itemDto;
    } */
}
