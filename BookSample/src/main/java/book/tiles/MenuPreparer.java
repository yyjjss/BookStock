package book.tiles;

import java.util.ArrayList;
import java.util.List;

import org.apache.tiles.Attribute;
import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;
import org.springframework.stereotype.Component;

//ViewPreparer: definition?„ ?‹¤?–‰?• ?•Œ ëª¨ë“  jsp?—?„œ ?“¸?ˆ˜ ?ˆ?Š” ?ë°”ê°ì²´ë?? ?ƒ?„±?•´ì¤?.
//Model(getter, setter)ê³? ê°™ì? ê¸°ëŠ¥ ?ƒ?„±?‹œ? ?´ ?‹¤ë¥? ë¿? 
//Model - ì»¨íŠ¸ë¡¤ëŸ¬ ?‹¤?–‰?‹œ | ViewPreparer - definition ?‹¤?–‰?‹œ
@Component
public class MenuPreparer implements ViewPreparer{

	@Override
	public void execute(Request tilesContext, AttributeContext attributeContext) {
		//List<String> menuList = new ArrayList<String>();
		//menuList.add("ë¡œê·¸?¸");
		//menuList.add("?šŒ?›ê°??…");
		//menuList ?´ë¦„ìœ¼ë¡? ?•´?‹¹ Attribute?— listë¥? ì¶”ê? , true?Š” ?—¬?Ÿ¬ ì»¨í…Œì¸ ì—?„œ ?“¸ ?ˆ˜ ?ˆ?„ë¡? ?—ˆ?š©?˜ ?˜ë¯?
		//attributeContext.putAttribute("menuList", new Attribute(menuList), true);
	}

	
}
