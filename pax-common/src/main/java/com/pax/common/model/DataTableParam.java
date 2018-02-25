package com.pax.common.model;

import org.apache.commons.lang.StringUtils;

public class DataTableParam {
	/// <summary>
    /// Request sequence number sent by DataTable, same value must be returned in response
    /// </summary>       
    private String sEcho;

    /// <summary>
    /// Text used for filtering
    /// </summary>
    private String sSearch;

    /// <summary>
    /// Number of records that should be shown in table
    /// </summary>
    private int iDisplayLength;

    /// <summary>
    /// First record that should be shown(used for paging)
    /// </summary>
    private int iDisplayStart;

    /// <summary>
    /// Number of columns in table
    /// </summary>
    private int iColumns;	

    /// <summary>
    /// Number of columns that are used in sorting
    /// </summary>
    private int iSortingCols;
    
    /// <summary>
    /// Index of the column that is used for sorting
    /// </summary>
    private int iSortCol_0;
    
    
    /// <summary>
    /// Sorting direction "asc" or "desc"
    /// </summary>
    private String sSortDir_0;

    /// <summary>
    /// Comma separated list of column names
    /// </summary>
    private String sColumns;
    

	public String getSEcho() {
		return sEcho;
	}

	public void setSEcho(String echo) {
		sEcho = echo;
	}

	public String getSSearch() {
		return sSearch;
	}

	public void setSSearch(String search) {
		sSearch = search;
	}

	public int getIDisplayLength() {
		return iDisplayLength;
	}

	public void setIDisplayLength(int displayLength) {
		iDisplayLength = displayLength;
	}

	public int getIDisplayStart() {
		return iDisplayStart;
	}

	public void setIDisplayStart(int displayStart) {
		iDisplayStart = displayStart;
	}

	public int getIColumns() {
		return iColumns;
	}

	public void setIColumns(int columns) {
		iColumns = columns;
	}

	public int getISortingCols() {
		return iSortingCols;
	}

	public void setISortingCols(int sortingCols) {
		iSortingCols = sortingCols;
	}	

	public int getISortCol_0() {
		return iSortCol_0;
	}

	public void setISortCol_0(int sortCol_0) {
		iSortCol_0 = sortCol_0;
	}

	public String getSSortDir_0() {
		return sSortDir_0;
	}

	public void setSSortDir_0(String sortDir_0) {
		sSortDir_0 = sortDir_0;
	}

	public String getSColumns() {
		return sColumns;
	}

	public void setSColumns(String columns) {
		sColumns = columns;
	}
    
}
