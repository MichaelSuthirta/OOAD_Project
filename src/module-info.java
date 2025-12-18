/**
 * 
 */
/**
 * 
 */
module OOAD_Project {
	requires java.sql;
	requires javafx.graphics;
	requires javafx.controls;
	requires javafx.base;
	
	opens main;
	opens models.entity_models to javafx.base;

}