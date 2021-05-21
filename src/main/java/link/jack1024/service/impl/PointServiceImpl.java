package link.jack1024.service.impl;

import link.jack1024.dao.PointDao;
import link.jack1024.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("pointService")
public class PointServiceImpl implements PointService {
    @Autowired
    private PointDao pointDao;
}
