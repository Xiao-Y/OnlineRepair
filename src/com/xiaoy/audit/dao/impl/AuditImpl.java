package com.xiaoy.audit.dao.impl;

import org.springframework.stereotype.Repository;

import com.xiaoy.audit.dao.AuditDao;
import com.xiaoy.base.dao.impl.CommonImpl;
import com.xiaoy.base.entites.Audit;

@Repository
public class AuditImpl extends CommonImpl<Audit> implements AuditDao{

}
