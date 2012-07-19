namespace java com.cloud9ers.director
namespace rb director

exception InstanceDoesNotExist {
	1: string description	
}

service LabsDirector {
	void freeze(1: string s) throws(1: InstanceDoesNotExist ex);
}
