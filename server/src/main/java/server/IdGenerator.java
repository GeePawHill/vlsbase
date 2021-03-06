package server;

public class IdGenerator {
	
	static int nextNoun=0;
	static int nextName=0;
	
	private static String[] names = {
			"Nick",
			"Paula",
			"Jimmy",
			"Jc",
			"Deana",
			"Loraine",
			"Donn",
			"Annette",
			"Pauline",
			"Rodger",
			"Maria",
			"Tomas",
			"Mindy",
			"Lynn",
			"Rae",
			"Brain",
			"Merle",
			"Pablo",
			"Jillian",
			"Rickey",
			"Charlotte",
			"Luis",
			"Lonny",
			"Doug",
			"Cordell",
			"Zelma",
			"Johnnie",
			"Janice",
			"Yesenia",
			"Tia",
			"Harley",
			"Seymour",
			"Keenan",
			"Merle",
			"Hiram",
			"Julia",
			"Katheryn",
			"Celeste",
			"Willis",
			"Hobert",
			"Nettie",
			"Deloris",
			"Elton",
			"Jonah",
			"Malcolm",
			"Ty",
			"Marcelo",
			"Christie",
			"Chase",
			"Augustus",
			"Jarvis",
			"Twila",
			"Rosario",
			"Neva",
			"Jodi",
			"John",
			"Lorenzo",
			"Sherry",
			"Chris",
			"Ricardo",
			"Bob",
			"Gay",
			"Johanna",
			"Diana",
			"Sebastian",
			"Elisha",
			"Eddie",
			"Odis",
			"Dawn",
			"Mohamed",
			"Fran",
			"Philip",
			"Rhett",
			"Carole",
			"Dollie",
			"Robin",
			"Hope",
			"Moses",
			"Edwardo",
			"Kenny",
			"Maryann",
			"Erasmo",
			"Flora",
			"Antonia",
			"Jessica",
			"Cheryl",
			"Osvaldo",
			"Abby",
			"Taylor",
			"Penelope",
			"Vance",
			"Bradly",
			"Carol",
			"Pete",
			"Clay",
			"Adrian",
			"Denny",
			"Susan",
			"Robert",
			"Keri",
			"Alissa",
			"Leland",
			"Seth",
			"Trisha",
			"Jamaal",
			"Jackie",
			"Hyman",
			"Mabel",
			"Jayne",
			"Wilmer",
			"Elinor",
			"Rachel",
			"Ernest",
			"Billie",
			"Sheri",
			"Mikel",
			"Jacob",
			"Bernardo",
			"Lorena",
			"Boyce",
			"Hoyt",
			"Bernadine",
			"Essie",
			"Wilson",
			"Lynn",
			"Fredric",
			"Ginger",
			"Daren",
			"Carlo",
			"Alden",
			"Perry",
			"Amie",
			"Hunter",
			"Kendrick",
			"Berta",
			"Russel",
			"Leanna",
			"Alberta",
			"Jerrod",
			"Isabel",
			"Ron",
			"Ellen",
			"Aline",
			"Britt",
			"Helene",
			"Rolf",
			"Brenda",
			"Lila",
			"Elise",
			"Dominique",
			"Deandre",
			"Ina",
			"Percy",
			"Bette",
			"Randall",
			"Antwan",
			"Jim",
			"Alexandria",
			"Charity",
			"Araceli",
			"Martha",
			"Jodie",
			"Edwina",
			"Antony",
			"Luke",
			"Heath",
			"Miquel",
			"Lakeisha",
			"Dexter",
			"Isiah",
			"Jaime",
			"Ellsworth",
			"Darron",
			"Hosea",
			"Adela",
			"John",
			"Von",
			"Gregorio",
			"Kirsten",
			"Cleveland",
			"Mavis",
			"Wendi",
			"Lacey",
			"Jon",
			"Karyn",
			"Gladys",
			"Matt",
			"Sang",
			"Bernard",
			"Mandy",
			"Tyrone",
			"Dewey",
			"Karin",
			"Ilene",
			"Reva",
			"Nickolas",
			"Letha",
			"Tammie",
			"Lana",
			"Eliseo",
	};
	
	private static String[] nouns = {
			"vehicle",
			"payment",
			"system",
			"preparation",
			"wedding",
			"drama",
			"world",
			"collection",
			"opportunity",
			"penalty",
			"negotiation",
			"tradition",
			"information",
			"situation",
			"health",
			"desk",
			"republic",
			"buyer",
			"method",
			"appearance",
			"client",
			"emphasis",
			"advice",
			"recognition",
			"king",
			"way",
			"child",
			"chocolate",
			"disaster",
			"sample",
			"funeral",
			"drawing",
			"presence",
			"society",
			"storage",
			"atmosphere",
			"hair",
			"height",
			"tennis",
			"independence",
			"chapter",
			"fishing",
			"requirement",
			"population",
			"priority",
			"recommendation",
			"topic",
			"location",
			"writer",
			"grandmother",
			"agency",
			"initiative",
			"enthusiasm",
			"loss",
			"wood",
			"boyfriend",
			"math",
			"growth",
			"knowledge",
			"childhood",
			"singer",
			"secretary",
			"family",
			"church",
			"wealth",
			"inflation",
			"product",
			"song",
			"advertising",
			"member",
			"investment",
			"marketing",
			"emotion",
			"personality",
			"championship",
			"importance",
			"year",
			"attention",
			"conversation",
			"disk",
			"country",
			"steak",
			"teacher",
			"midnight",
			"improvement",
			"inspection",
			"night",
			"environment",
			"guidance",
			"foundation",
			"committee",
			"dinner",
			"impression",
			"development",
			"mud",
			"temperature",
			"employee",
			"assignment",
			"language",
			"memory",
			"protection",
			"feedback",
			"assumption",
			"device",
			"difference",
			"death",
			"literature",
			"customer",
			"two",
			"science",
			"session",
			"obligation",
			"lady",
			"patience",
			"tale",
			"reputation",
			"measurement",
			"marriage",
			"comparison",
			"event",
			"football",
			"difficulty",
			"bath",
			"bird",
			"mixture",
			"chest",
			"passion",
			"farmer",
			"worker",
			"software",
			"industry",
			"application",
			"percentage",
			"video",
			"throat",
			"entertainment",
			"freedom",
			"scene",
			"role",
			"indication",
			"art",
			"version",
			"competition",
			"month",
			"direction",
			"housing",
			"heart",
			"town",
			"response",
			"department",
			"message",
			"candidate",
			"movie",
			"length",
			"understanding",
			"association",
			"problem",
			"cookie",
			"statement",
			"winner",
			"significance",
			"discussion",
			"opinion",
			"diamond",
			"energy",
			"baseball",
			"bathroom",
			"cancer",
			"breath",
			"girlfriend",
			"orange",
			"girl",
			"insurance",
			"connection",
			"agreement",
			"revolution",
			"relation",
			"permission",
			"manager",
			"success",
			"technology",
			"aspect",
			"database",
			"restaurant",
			"departure",
			"appointment",
			"description",
			"army",
			"college",
			"operation",
			"relationship",
			"tooth",
			"decision",
			"instruction",
			"policy",
			"variation",
			"recipe",
			"transportation",
			"professor",
			"office",
	};

	public static String noun(String prefix)
	{
		nextNoun+=1;
		int modded = nextNoun%nouns.length;
		int divved = nextNoun/nouns.length;
		return make(prefix, divved,upper(nouns[modded]));
	}

	private static String make(String prefix, int divved, String word) {
		String div = divved!=0 ? Integer.toString(divved) : "";
		return prefix+div+word;
	}

	private static String upper(String in) {
		String last = in.substring(1);
		String first = in.substring(0, 1);
		return first.toUpperCase()+last;
	}
	
	public static String name(String prefix)
	{
		nextName+=1;
		int modded = nextName%names.length;
		int divved = nextName/names.length;
		return make(prefix, divved , names[modded]);
	}


	
}
