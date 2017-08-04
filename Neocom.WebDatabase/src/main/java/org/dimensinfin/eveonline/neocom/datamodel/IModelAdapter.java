//	PROJECT:      NeoCom.Databases (NEOC.D)
//	AUTHORS:      Adam Antinoo - adamantinoo.git@gmail.com
//	COPYRIGHT:    (c) 2017 by Dimensinfin Industries, all rights reserved.
//	ENVIRONMENT:	Java 1.8 Library.
//	DESCRIPTION:	SQLite database access library. Isolates Neocom database access from any
//								environment limits.
//								Database and model adaptations for storage model independency.
package org.dimensinfin.eveonline.neocom.datamodel;

import org.dimensinfin.core.model.RootNode;

// - CLASS IMPLEMENTATION ...................................................................................
public interface IModelAdapter {
	// - M E T H O D - S E C T I O N ........................................................................
	public RootNode collaborate2Model();

	public DataSourceLocator getDataSourceLocator();
}

// - UNUSED CODE ............................................................................................
