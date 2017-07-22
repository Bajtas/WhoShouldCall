namespace WSCData.Migrations
{
    using System.Data.Entity.Migrations;

    public partial class ChangesForImageStoring : DbMigration
    {
        public override void Up()
        {
            AddColumn("dbo.Images", "Extension", c => c.String());
        }
        
        public override void Down()
        {
            DropColumn("dbo.Images", "Extension");
        }
    }
}