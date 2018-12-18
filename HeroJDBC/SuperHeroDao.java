public interface SuperHeroDao{
  public abstract void createSuperHerp(String heroName) throws SQLException;
  public abstract List<SuperHero> getSuperHeroList() throws SQLException;
}
