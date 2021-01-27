package ru.ystu.labs_store.model;import org.springframework.validation.annotation.Validated;import javax.persistence.*;import java.text.DecimalFormat;@Validated@Entity@Table(name = "history")public class History {	@Id	@GeneratedValue(strategy = GenerationType.IDENTITY)	private int id;	private Double price;	private String dateTimeOrder;	@ManyToOne	@JoinColumn(name = "id_user", nullable = false)	private User user;	public History() {	}	public History(double price, String dateTimeOrder, User user) {		this.price = price;		this.dateTimeOrder = dateTimeOrder;		this.user = user;	}	public void setId(int id) {		this.id = id;	}	public int getId() {		return id;	}	public void setDateTimeOrder(String dateTimeOrder) {		this.dateTimeOrder = dateTimeOrder;	}	public void setPrice(Double price) {		this.price = price;	}	public Double getPrice() {		return price;	}	public String getDateTimeOrder() {		return dateTimeOrder;	}	public void setUser(User user) {		this.user = user;	}	public User getUser() {		return user;	}	public String getName() {		DecimalFormat format = new DecimalFormat("###,###,##0.#");		String price = format.format(getPrice()) + " ₽";		return "Заказ от " + dateTimeOrder + " №" + id + ". " + price;	}}