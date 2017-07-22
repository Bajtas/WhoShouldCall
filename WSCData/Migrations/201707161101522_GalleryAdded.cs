namespace WSCData.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class GalleryAdded : DbMigration
    {
        public override void Up()
        {
            CreateTable(
                "dbo.ImageGalleries",
                c => new
                    {
                        Id = c.Int(nullable: false, identity: true),
                    })
                .PrimaryKey(t => t.Id);
            
            CreateTable(
                "dbo.Images",
                c => new
                    {
                        Id = c.Int(nullable: false, identity: true),
                        Data = c.Binary(),
                        ImageGallery_Id = c.Int(),
                    })
                .PrimaryKey(t => t.Id)
                .ForeignKey("dbo.ImageGalleries", t => t.ImageGallery_Id)
                .Index(t => t.ImageGallery_Id);
            
            AddColumn("dbo.FoodProviders", "ImageGallery_Id", c => c.Int());
            CreateIndex("dbo.FoodProviders", "ImageGallery_Id");
            AddForeignKey("dbo.FoodProviders", "ImageGallery_Id", "dbo.ImageGalleries", "Id");
        }
        
        public override void Down()
        {
            DropForeignKey("dbo.FoodProviders", "ImageGallery_Id", "dbo.ImageGalleries");
            DropForeignKey("dbo.Images", "ImageGallery_Id", "dbo.ImageGalleries");
            DropIndex("dbo.Images", new[] { "ImageGallery_Id" });
            DropIndex("dbo.FoodProviders", new[] { "ImageGallery_Id" });
            DropColumn("dbo.FoodProviders", "ImageGallery_Id");
            DropTable("dbo.Images");
            DropTable("dbo.ImageGalleries");
        }
    }
}
