package com.boot.common.util;

import java.io.StringWriter;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class XmlUtils
{
	/**
	 * 生成xml表头
	 * 
	 * @param document
	 * @return
	 */
	public static Document createHeader(String... encoding) throws Exception
	{
		String isEncoding = FileUtils.isEncoding(encoding);
		Document document = DocumentHelper.createDocument();
		document.setXMLEncoding(isEncoding);
		return document;
	}

	/**
	 * 根据给定的内容生成xml
	 * 
	 * @param xml
	 *            字符串xml内容
	 * @return Document的xml对象
	 * @throws Exception
	 */
	public static Document createDocument(String xml) throws Exception
	{
		Document document = DocumentHelper.parseText(xml);
		return document;
	}

	/**
	 * 生成xml下的子节点
	 * 
	 * @param parent
	 *            父节点
	 * @param nodeName
	 *            要生成的子节点名字
	 * @param value
	 *            要生成的子节点的值
	 * @param isCDATA
	 *            是否需要CDATA转义,true:需要，false:不需要
	 */
	public static Element createChildText(Element parent, String nodeName, String value, boolean... isCDATA) throws Exception
	{
		boolean isTrue;
		if (isCDATA.length > 0)
		{
			isTrue = isCDATA[0];
		}
		else
		{
			isTrue = true;
		}

		Element child = parent.addElement(nodeName);
		if (isTrue)
		{
			child.addCDATA(value);
			// child.setText("<![CDATA["+value+"]]>");
		}
		else
		{
			child.setText(value);
		}

		return child;
	}

	/**
	 * 生成xml下的节点
	 * 
	 * @param elemnt
	 *            节点名字
	 * @param value
	 *            要生成的节点的值
	 * @param isCDATA
	 *            是否需要CDATA转义,true:需要，false:不需要
	 */
	public static Element createText(Element elemnt, String value, boolean... isCDATA) throws Exception
	{
		boolean isTrue;
		if (isCDATA.length > 0)
		{
			isTrue = isCDATA[0];
		}
		else
		{
			isTrue = true;
		}

		if (isTrue)
		{
			String text = elemnt.getText();
			if (text.equals(""))
			{
				elemnt.addCDATA(value);
			}
			else
			{
				String name = elemnt.getName();
				Element parent = elemnt.getParent();
				parent.remove(elemnt);
				Element child = parent.addElement(name);
				child.addCDATA(value);
			}
			// elemnt.setText("<![CDATA["+value+"]]>");
		}
		else
		{
			elemnt.setText(value);
		}

		return elemnt;
	}

	public static Element createChildAttribute(Element parent, String nodeName, String... param) throws Exception
	{
		Element child = parent.addElement(nodeName);
		for (int i = 0; i < param.length; i += 2)
		{
			child.addAttribute(param[i], param[i + 1]);
		}
		return child;
	}

	public static Element createAttribute(Element nodeName, String... param) throws Exception
	{
		for (int i = 0; i < param.length; i += 2)
		{
			nodeName.addAttribute(param[i], param[i + 1]);
		}
		return nodeName;
	}

	public static String getNodeText(Node node) throws Exception
	{
		if (node != null)
		{
			return node.getText().trim();
		}
		else
		{
			return "";
		}
	}

	public static String getNodeTextNoTrim(Node node) throws Exception
	{
		if (node != null)
		{
			return node.getText();
		}
		else
		{
			return "";
		}
	}

	public static String getChildAttributeValue(Element parent, String nodeName, String attribute) throws Exception
	{
		Element node = parent.element(nodeName);
		if (node != null)
		{
			if (node.attributeValue(attribute) != null) {
				return node.attributeValue(attribute);
			} else {
				return "";
			}
		}
		else
		{
			return "";
		}
	}

	public static String getAttributeValue(Element node, String attribute) throws Exception
	{
		if (node != null)
		{
			if (node.attributeValue(attribute) != null) {
				return node.attributeValue(attribute);
			} else {
				return "";
			}
		}
		else
		{
			return "";
		}
	}

	public static void removeChild(Element parent, String childNode) throws Exception
	{
		Element child = parent.element(childNode);
		parent.remove(child);
	}

	/**
	 * 格式化XML文档
	 * 
	 * @param document
	 *            xml文档
	 * @param charset
	 *            字符串的编码
	 * @param istrans
	 *            是否对属性和元素值进行转移
	 * @return 格式化后XML字符串
	 */
	public static String formatXml(Document document, String... encoding) throws Exception
	{
		String isEncoding = FileUtils.isEncoding(encoding);

		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding(isEncoding);
		StringWriter sw = new StringWriter();
		XMLWriter xw = null;
		try
		{
			xw = new XMLWriter(sw, format);
			xw.setEscapeText(false);
			xw.write(document);
		} finally
		{
			if (xw != null)
			{
				xw.flush();
				xw.close();
			}
		}

		return sw.toString();
	}

}
