package user35.servlet;

import java.io.Serializable;

public class ProductItem implements Serializable
{
	int catId;
	int prodId;
	float price;
	
	
	/**
	 *	No Argument Constructor 
	 */
	public ProductItem() 
	{
		super();
	}


	/**
	 * @param catId
	 * @param prodId
	 * @param price
	 */
	public ProductItem(int catId, int prodId, float price) 
	{
		super();
		this.catId = catId;
		this.prodId = prodId;
		this.price = price;
	}


	/**
	 * @return the catId
	 */
	public int getCatId() 
	{
		return catId;
	}


	/**
	 * @param catId the catId to set
	 */
	public void setCatId(int catId) 
	{
		this.catId = catId;
	}


	/**
	 * @return the prodId
	 */
	public int getProdId() 
	{
		return prodId;
	}


	/**
	 * @param prodId the prodId to set
	 */
	public void setProdId(int prodId) 
	{
		this.prodId = prodId;
	}


	/**
	 * @return the price
	 */
	public float getPrice() 
	{
		return price;
	}


	/**
	 * @param price the price to set
	 */
	public void setPrice(float price) 
	{
		this.price = price;
	}
	
	
	
	
}
