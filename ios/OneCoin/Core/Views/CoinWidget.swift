//
//  CoinWidget.swift
//  OneCoin
//
//  Created by Виталий Перятин on 22.04.2023.
//

import SwiftUI
import OneCoinShared

struct CoinWidget<Content: View>: View {
    var title: String
    var withBorder: Bool = false
    var withHorizontalPadding: Bool = true
    var onClick: (() -> ())? = nil
    var content: () -> Content
    
    var body: some View {
        VStack(alignment: .leading, spacing: 0) {
            CoinWidgetTitle(title: title, onClick: onClick)
            CoinWidgetContent<Content>(
                withBorder: withBorder,
                withHorizontalPadding: withHorizontalPadding,
                content: content
            )
        }
    }
}

private struct CoinWidgetTitle: View {
    private let theme = CoinTheme.shared
    
    var title: String
    var onClick: (() -> ())? = nil
    
    var body: some View {
        let clickEnabled = onClick != nil
        HStack(alignment: .center) {
            Text(title)
                .fontH5Style(color: theme.colors.content)
            if (clickEnabled) {
                Spacer()
                // TODO: replace ic_arrow_down to ic_arrow_next_small
                SVGImageView(url: MR.files().ic_arrow_down.url)
                    .tintColor(theme.colors.content)
                    .frameSvg(width: 24, height: 24)
            }
        }
            .onTapGestureIf(clickEnabled) {
                onClick?()
            }
            .frame(maxWidth: .infinity, alignment: .leading)
            .padding(.vertical, UI.Padding.Vertical.default)
            .padding(.horizontal, UI.Padding.Horizontal.default)
    }
}

private struct CoinWidgetContent<Content: View>: View {
    private let withBorder: Bool
    private let withHorizontalPadding: Bool
    private let content: () -> Content
    
    init(
        withBorder: Bool = false, 
        withHorizontalPadding: Bool = true, 
        content: @escaping () -> Content
    ) {
        self.withBorder = withBorder
        self.withHorizontalPadding = withHorizontalPadding
        self.content = content
    }
    
    var body: some View {
        let contentHorizontalPadding = withHorizontalPadding ? UI.Padding.Horizontal.default : 0
        let borderRadius: CGFloat = withBorder ? 12 : 0
        let shape = withBorder ? RoundedRectangle(cornerRadius: borderRadius) : RoundedRectangle(cornerRadius: 0)
        
        content()
            .frame(maxWidth: .infinity, alignment: .leading)
            .background(CoinTheme.shared.colors.background)
            .clipShape(shape)
            .padding(.horizontal, contentHorizontalPadding)
            .padding(.top, 2)
            .shadow(radius: withBorder ? 1 : 0)
    }
}


struct CoinWidget_Previews: PreviewProvider {
    static var previews: some View {
        CoinWidget(
            title: "Title",
            withBorder: true,
            withHorizontalPadding: true
        ) {
            Text("Content")
        }
    }
}
