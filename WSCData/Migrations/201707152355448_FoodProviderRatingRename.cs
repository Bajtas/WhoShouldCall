namespace WSCData.Migrations
{
    using System.Data.Entity.Migrations;

    public partial class FoodProviderRatingRename : DbMigration
    {
        public override void Up()
        {
            AddColumn("dbo.FoodProviders", "Rating", c => c.Decimal(nullable: false, precision: 18, scale: 2));
            DropColumn("dbo.FoodProviders", "FoodProviderRating");
        }
        
        public override void Down()
        {
            AddColumn("dbo.FoodProviders", "FoodProviderRating", c => c.Decimal(nullable: false, precision: 18, scale: 2));
            DropColumn("dbo.FoodProviders", "Rating");
        }
    }
}
