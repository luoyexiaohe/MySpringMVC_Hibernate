package hyf.model.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import JDBC.JDBCUtil;

@Transactional
@Service
public class BaseFacade {

	@Autowired
	protected JDBCUtil Dao ;
	
}
