package org.springybot.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springybot.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springybot.dto.ConfigDTO;
import org.springybot.dto.SpringyBotDTO;
import org.springybot.repository.ConfigRepository;
import org.springybot.repository.SpringyBotRepository;
import org.springybot.service.SpringyBotService;
import org.springybot.utils.CommUtils;
import org.springybot.utils.RedisUtils;
import org.springybot.utils.ResponseUtils;
import org.springybot.utils.RetEnum;
import org.springybot.utils.ResponseUtils.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;

@Slf4j
@Service
public class SpringyBotServiceImpl implements SpringyBotService {

    @Autowired
    private SpringyBotRepository springyBotRepository;

    @Autowired
    private ConfigRepository configRepository;

    @Autowired
    private RedisUtils redisUtils;

    // SpringyBot CRUD
    @Override
    public Optional<SpringyBot> findById(Long id) {
        return springyBotRepository.findById(id);
    }

    @Override
    public List<SpringyBot> findAllByPage(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return springyBotRepository.findAll(pageable).getContent();
    }

    @Override
    public List<SpringyBot> findAll() {
        return springyBotRepository.findAll();
    }

    @Override
    public SpringyBot findByUsername(String username) {
        return springyBotRepository.findByUsername(username);
    }

    @Override
    public SpringyBot findByToken(String token) {
        return springyBotRepository.findByToken(token);
    }

    @Override
    public void deleteById(Long id) {
        springyBotRepository.deleteById(id);
    }

    @Override
    public void save(SpringyBot springyBot) {
        springyBotRepository.save(springyBot);
    }

    @Override
    public List<RecordChannelUsers> findRecordChannelUsersBySpringyBotId(Long id) {
        return springyBotRepository.findRecordChannelUsersBySpringyBotId(id);
    }

    @Override
    public List<RecordGroupUsers> findRecordGroupUsersBySpringyBotId(Long id) {
        return springyBotRepository.findRecordGroupUsersBySpringyBotId(id);
    }

    @Override
    public List<InvitationThreshold> findInvitationThresholdBySpringyBotId(Long id) {
        return springyBotRepository.findInvitationThresholdBySpringyBotId(id);
    }

    @Override
    public List<RobotGroupManagement> findRobotGroupManagementBySpringyBotId(Long id) {
        return springyBotRepository.findRobotGroupManagementBySpringyBotId(id);
    }

    @Override
    public List<RobotChannelManagement> findRobotChannelManagementBySpringyBotId(Long id) {
        return springyBotRepository.findRobotChannelManagementBySpringyBotId(id);
    }

    @Override
    public List<WhiteList> findWhiteListBySpringyBotId(Long id) {
        return springyBotRepository.findWhiteListBySpringyBotId(id);
    }

    @Override
    public List<TgUser> findTgUserBySpringyBotId(Long id) {
        return springyBotRepository.findTgUserBySpringyBotId(id);
    }

    @Override
    public TgUser findTgUserBySpringyBotIdAndUserId(Long id, String userId) {
        return springyBotRepository.findTgUserBySpringyBotIdAndUserId(id, userId);
    }

    @Override
    public List<InvitationBonusUser> findInvitationBonusUserBySpringyBotId(Long id) {
        return springyBotRepository.findInvitationBonusUserBySpringyBotId(id);
    }

    @Override
    public Optional<Config> findByConfigId(Long id) {
        return configRepository.findById(id);
    }

    @Override
    public void saveConfig(Config config) {
        configRepository.save(config);
    }

    @Override
    public SpringyBot cacheSpringyBotDataToRedis(String token) {
        SpringyBot springyBot = findByToken(token);
        Long id = springyBot.getId();
        Config config = springyBot.getConfig();
        List<RecordGroupUsers> recordGroupUsers = findRecordGroupUsersBySpringyBotId(id);
        List<RecordChannelUsers> recordChannelUsers = findRecordChannelUsersBySpringyBotId(id);
        List<InvitationThreshold> invitationThreshold = findInvitationThresholdBySpringyBotId(id);
        List<RobotGroupManagement> robotGroupManagements = findRobotGroupManagementBySpringyBotId(id);
        List<RobotChannelManagement> robotChannelManagements = findRobotChannelManagementBySpringyBotId(id);
        redisUtils.set("RobotChannelManagement_" + id, robotChannelManagements);
        redisUtils.set("RobotGroupManagement_" + id, robotGroupManagements);
        redisUtils.set("Config_" + id, config);
        redisUtils.set("RecordGroupUsers_" + id, recordGroupUsers);
        redisUtils.set("RecordChannelUsers_" + id, recordChannelUsers);
        redisUtils.set("InvitationThreshold_" + id, invitationThreshold);
        return springyBot;
    }

    @Override
    public ResponseEntity<ResponseData> addBot(SpringyBotDTO springyBotDTO) {
        SpringyBot springyBot = new SpringyBot();
        springyBot.setToken(springyBotDTO.getToken());
        springyBot.setUsername(springyBotDTO.getUsername());
        springyBot.setBotModel(springyBotDTO.getBotModel());
        springyBot.setBotType(springyBotDTO.getBotType());
        springyBot.setState(springyBotDTO.getState());

        Config config = new Config();
        config.setContactPerson("");
        config.setDeleteSeconds(0);
        config.setFollowChannelSet(false);
        config.setFollowChannelSet_chatId(0L);
        config.setFollowChannelSet_chatTitle("");
        config.setInvitationBonusSet(false);
        config.setInviteEarnedOutstand(BigDecimal.valueOf(0));
        config.setMinimumPayout(BigDecimal.valueOf(0));
        config.setInviteFriendsAutoClearTime(0);
        config.setInviteFriendsQuantity(0);
        config.setInviteFriendsSet(false);
        config.setInviteMembers(0);
        config.setPassword("duv!3qz@XY");
        springyBot.setConfig(config);
        save(springyBot);

        log.info("SpringyBotServiceImpl ==> addBot ... [ {} ] 新增成功", springyBotDTO.getUsername());
        return ResponseUtils.response(RetEnum.RET_SUCCESS, "新增成功");
    }

    @Override
    public ResponseEntity<ResponseData> getAllBot(int page, int pageSize) {

        HashMap<Object, Object> data = new HashMap<>();
        List<SpringyBot> springyBotAllList = findAllByPage(page, pageSize);

        Object pager = CommUtils.Pager(page, pageSize, springyBotAllList.size());
        data.put("list", springyBotAllList);
        data.put("pager", pager);

        return ResponseUtils.response(RetEnum.RET_SUCCESS, data);
    }

    @Override
    public ResponseEntity<ResponseData> updateBot(SpringyBotDTO springyBotDTO) {
        Long id = springyBotDTO.getId();
        SpringyBot springyBot = findById(id).get();

        springyBot.setUsername(springyBotDTO.getUsername());
        springyBot.setToken(springyBotDTO.getToken());
        springyBot.setBotModel(springyBotDTO.getBotModel());
        springyBot.setBotType(springyBotDTO.getBotType());

        if (springyBot.getConfig() == null) {
            Config config = new Config();
            config.setContactPerson("");
            config.setDeleteSeconds(10);
            config.setFollowChannelSet(false);
            config.setFollowChannelSet_chatId(0L);
            config.setFollowChannelSet_chatTitle("");
            config.setInvitationBonusSet(false);
            config.setInviteEarnedOutstand(BigDecimal.valueOf(0));
            config.setMinimumPayout(BigDecimal.valueOf(0));
            config.setInviteFriendsAutoClearTime(0);
            config.setInviteFriendsQuantity(0);
            config.setInviteFriendsSet(false);
            config.setInviteMembers(0);
            config.setPassword("");
            springyBot.setConfig(config);
        }

        save(springyBot);
        log.info("SpringyBotServiceImpl ==> updateBot ... [ {} ] 修改成功", springyBotDTO.getId());
        return ResponseUtils.response(RetEnum.RET_SUCCESS, "修改成功");
    }

    @Override
    public ResponseEntity<ResponseData> updateConfig(ConfigDTO configDTO) {
        Long id = configDTO.getId();
        Config config = findByConfigId(id).get();
        config.setContactPerson(configDTO.getContactPerson());
        config.setDeleteSeconds(configDTO.getDeleteSeconds());
        config.setFollowChannelSet(configDTO.getFollowChannelSet());
        config.setFollowChannelSet_chatId(configDTO.getFollowChannelSet_chatId());
        config.setFollowChannelSet_chatTitle(configDTO.getFollowChannelSet_chatTitle());
        config.setInvitationBonusSet(configDTO.getInvitationBonusSet());
        config.setInviteEarnedOutstand(configDTO.getInviteEarnedOutstand());
        config.setMinimumPayout(configDTO.getMinimumPayout());
        config.setInviteFriendsAutoClearTime(configDTO.getInviteFriendsAutoClearTime());
        config.setInviteFriendsQuantity(configDTO.getInviteFriendsQuantity());
        config.setInviteFriendsSet(configDTO.getInviteFriendsSet());
        config.setInviteMembers(configDTO.getInviteMembers());
        config.setPassword(configDTO.getPassword());
        saveConfig(config);

        List<SpringyBot> springyBots = findAll();

        springyBots.stream().filter(s -> s.getConfig().getId().equals(id)).findAny().ifPresent(s -> {
            redisUtils.set("Config_" + s.getId(), config);
        });

        log.info("SpringyBotServiceImpl ==> updateConfig ... [ {} ] 修改成功", configDTO.getId());
        return ResponseUtils.response(RetEnum.RET_SUCCESS, "修改成功");
    }

    @Override
    public ResponseEntity<ResponseData> deleteBot(Map<String, String> requestData) {
        String[] ids = requestData.get(requestData.keySet().toArray()[0]).split(",");

        for (String id : ids) {
            Long parseId = Long.parseLong(id);
            deleteById(parseId);
            log.info("SpringyBotServiceImpl ==> deleteBot ... [ {} ] 刪除成功", id);
        }
        return ResponseUtils.response(RetEnum.RET_SUCCESS, "删除成功");
    }

    @Override
    public ResponseEntity<ResponseData> getRunTime() {
        return ResponseUtils.response(RetEnum.RET_SUCCESS, "");
    }

    @Override
    public ResponseEntity<ResponseData> fetchManagedChat(ConfigDTO configDTO) {
        Long configId = configDTO.getId();
        List<SpringyBot> springyBots = findAll();
        List<RobotChannelManagement> chats = new ArrayList<>();
        HashMap<Object, Object> data = new HashMap<>();
        springyBots.stream().filter(s -> s.getConfig().getId().equals(configId)).findAny()
                .ifPresent(springybot -> {
                    List<RobotChannelManagement> robotChannelManagements = findRobotChannelManagementBySpringyBotId(
                            springybot.getId());
                    robotChannelManagements.stream().forEach(rcm -> {
                        chats.add(rcm);
                    });
                });
        data.put("list", chats);
        return ResponseUtils.response(RetEnum.RET_SUCCESS, data);
    }


}