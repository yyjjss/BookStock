package book.tiles;

import java.util.ArrayList;
import java.util.List;

import org.apache.tiles.Attribute;
import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;
import org.springframework.stereotype.Component;

//ViewPreparer: definition?�� ?��?��?��?�� 모든 jsp?��?�� ?��?�� ?��?�� ?��바객체�?? ?��?��?���?.
//Model(getter, setter)�? 같�? 기능 ?��?��?��?��?�� ?���? �? 
//Model - 컨트롤러 ?��?��?�� | ViewPreparer - definition ?��?��?��
@Component
public class MenuPreparer implements ViewPreparer{

	@Override
	public void execute(Request tilesContext, AttributeContext attributeContext) {
		//List<String> menuList = new ArrayList<String>();
		//menuList.add("로그?��");
		//menuList.add("?��?���??��");
		//menuList ?��름으�? ?��?�� Attribute?�� list�? 추�? , true?�� ?��?�� 컨테츠에?�� ?�� ?�� ?��?���? ?��?��?�� ?���?
		//attributeContext.putAttribute("menuList", new Attribute(menuList), true);
	}

	
}
